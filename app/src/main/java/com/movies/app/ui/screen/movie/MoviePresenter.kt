package com.movies.app.ui.screen.movie

import com.movies.app.ui.common.model.Loading
import com.movies.app.ui.common.model.Model
import com.movies.app.data.api.model.Movie
import com.movies.app.data.repository.MovieRepository
import com.movies.app.misc.flatMapToViewState
import com.movies.app.misc.resetWith
import com.movies.app.ui.base.BasePresenter
import io.reactivex.Observable
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class MoviePresenter @Inject constructor(
  private val movieRepository: MovieRepository
) : BasePresenter<MovieView, MovieResult, MovieUIState>() {

  override fun initialState(): MovieUIState = MovieUIState()

  override fun bind() {
    val moviesObs =
      Observable.merge(
          intent(MovieView::onSearchQuery)
              .filter { it.isBlank() }
              .skip(1)
              .debounce(300, MILLISECONDS)
              .distinctUntilChanged(),
          intent(MovieView::onCreated)
              .firstElement()
              .toObservable())
          .map { 1 }
          .flatMapToViewState(
              { movieRepository.getMovies(forceRemote = true) },
              { MoviesPartialViewState(it) },
              { ErrorPartialViewState(it.localizedMessage) },
              { LoadingPartialViewState() }
          )

    val loadMoreMoviesObs = intent(MovieView::loadMore)
        .distinctUntilChanged()
        .flatMapToViewState(
            {
              if (it.second.isBlank()) {
                movieRepository.getMovies(forceRemote = true, page = it.first)
              } else {
                movieRepository.search(page = it.first, query = it.second)
              }
            },
            { MoreMoviesPartialViewState(it) },
            { ErrorPartialViewState(it.localizedMessage) },
            { LoadingPartialViewState() }
        )

    val searchMoviesObs = intent(MovieView::onSearchQuery)
        .filter { it.isNotBlank() }
        .debounce(300, MILLISECONDS)
        .distinctUntilChanged()
        .flatMapToViewState(
            {
              if (it.isBlank()) {
                movieRepository.getMovies(forceRemote = true)
              } else {
                movieRepository.search(query = it)
              }
            },
            { MoviesPartialViewState(it) },
            { ErrorPartialViewState(it.localizedMessage) }
        )

    val movieDetailsObs = intent(MovieView::movieClicked)
        .map { MovieDetailsPartialViewState(it) }
        .resetWith(MovieDetailsPartialViewState(null))

    subscribeForStateUpdates(
        mergeResults(moviesObs, loadMoreMoviesObs, searchMoviesObs, movieDetailsObs),
        MovieView::update
    )
  }

  override fun reduce(
    state: MovieUIState,
    result: MovieResult
  ): MovieUIState = when (result) {
    is LoadingPartialViewState -> state.copy(
        movies = state.movies?.plus(listOf(Loading()))
    )
    is ErrorPartialViewState -> state.copy(
        error = result.error
    )
    is MoviesPartialViewState -> state.copy(
        nextPage = result.nextPage,
        movies = result.movies
    )
    is MoreMoviesPartialViewState -> state.copy(
        nextPage = result.nextPage,
        movies = combineResults(state.movies, result.movies)
    )
    is MovieDetailsPartialViewState -> state.copy(
        movies = null,
        movieDetails = result.movieDetails,
    )
  }

  private fun combineResults(
    previous: List<Model<Int>>?,
    new: List<Model<Int>>
  ): List<Model<Int>> {
    val results = ArrayList<Model<Int>>()
    previous?.let { results.addAll(it.filterIsInstance<Movie>()) }
    results.addAll(new)
    return results
  }
}
