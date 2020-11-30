package com.movies.app.ui.movie

import com.movies.app.common.model.Loading
import com.movies.app.common.model.Model
import com.movies.app.data.repository.MovieRepository
import com.movies.app.misc.flatMapToViewState
import com.movies.app.misc.switchNavigationState
import com.movies.app.ui.base.BasePresenter
import javax.inject.Inject

class MoviePresenter @Inject constructor(
  private val movieRepository: MovieRepository
) : BasePresenter<MovieView, MovieResult, MovieUIState>() {

  override fun initialState(): MovieUIState = MovieUIState()

  override fun bind() {
    val moviesObs = intent(MovieView::onCreated)
        .flatMapToViewState(
            { movieRepository.getMovies(forceRemote = true) },
            { MoviesPartialViewState(it) },
            { ErrorPartialViewState(it.localizedMessage) },
            { LoadingPartialViewState() }
        )

    val loadMoreMoviesObs = intent(MovieView::loadMore)
        .distinctUntilChanged()
        .flatMapToViewState(
            { movieRepository.getMovies(forceRemote = true, page = it) },
            { MoviesPartialViewState(it) },
            { ErrorPartialViewState(it.localizedMessage) },
            { LoadingPartialViewState() }
        )

    val movieDetailsObs = intent(MovieView::movieClicked)
        .map { MovieDetailsPartialViewState(it) }
        .switchNavigationState(
            { it is MovieDetailsPartialViewState },
            { MovieDetailsPartialViewState(null) }
        )

    subscribeForStateUpdates(
        mergeResults(moviesObs, loadMoreMoviesObs, movieDetailsObs),
        MovieView::update
    )
  }

  override fun reduce(
    state: MovieUIState,
    result: MovieResult
  ): MovieUIState = when (result) {
    is LoadingPartialViewState -> state.copy(
        movies = state.movies + listOf(Loading())
    )
    is ErrorPartialViewState -> state.copy(
        error = result.error
    )
    is MoviesPartialViewState -> state.copy(
        nextPage = result.nextPage,
        movies = combineResults(state.movies, result.movies)
    )
    is MovieDetailsPartialViewState -> state.copy(
        movieDetails = result.movieDetails
    )
  }

  private fun combineResults(
    previous: List<Model<Int>>,
    new: List<Model<Int>>
  ): List<Model<Int>> {
    val results = ArrayList<Model<Int>>()
    results.addAll(previous)
    results.addAll(new)
    // Remove loading
    results.removeAt(previous.size - 1)
    return results
  }
}
