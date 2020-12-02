package com.movies.app.ui.screen.movie_details

import com.movies.app.data.repository.MovieRepository
import com.movies.app.misc.flatMapToViewState
import com.movies.app.misc.resetWith
import com.movies.app.ui.base.BasePresenter
import com.movies.app.ui.screen.movie.MovieDetailsPartialViewState
import javax.inject.Inject

class MovieDetailsPresenter @Inject constructor(
  private val movieRepository: MovieRepository
) : BasePresenter<MovieDetailsView, MovieDetailsResult, MovieDetailsUIState>() {

  override fun initialState(): MovieDetailsUIState = MovieDetailsUIState()

  override fun bind() {
    val moviesObs = intent(MovieDetailsView::onCreated)
        .flatMapToViewState(
            { movieRepository.getMovieById(forceRemote = true, id = it) },
            { MoviePartialViewState(it) },
            { ErrorPartialViewState(it.localizedMessage) },
            { LoadingPartialViewState(true) }
        )

    val backObs = intent(MovieDetailsView::backClicked)
        .map { BackPartialViewState(true) }
        .resetWith(BackPartialViewState(false))

    subscribeForStateUpdates(
        mergeResults(moviesObs, backObs),
        MovieDetailsView::update
    )
  }

  override fun reduce(
    state: MovieDetailsUIState,
    result: MovieDetailsResult
  ): MovieDetailsUIState = when (result) {
    is LoadingPartialViewState -> state.copy(
        loading = result.loading
    )
    is ErrorPartialViewState -> state.copy(
        error = result.error
    )
    is MoviePartialViewState -> state.copy(
        loading = false,
        movie = result.movie
    )
    is BackPartialViewState -> state.copy(
        movie = null,
        back = result.back
    )
  }
}
