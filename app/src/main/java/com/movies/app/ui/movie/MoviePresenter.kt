package com.movies.app.ui.movie

import com.movies.app.data.repository.MovieRepository
import com.movies.app.misc.flatMapToViewState
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
            { LoadingPartialViewState(true) }
        )

    subscribeForStateUpdates(
        mergeResults(moviesObs),
        MovieView::update
    )
  }

  override fun reduce(
    state: MovieUIState,
    result: MovieResult
  ): MovieUIState = when (result) {
    is LoadingPartialViewState -> state.copy(
        loading = result.loading
    )
    is ErrorPartialViewState -> state.copy(
        error = result.error
    )
    is MoviesPartialViewState -> state.copy(
        loading = false,
        movies = result.movies
    )
  }
}
