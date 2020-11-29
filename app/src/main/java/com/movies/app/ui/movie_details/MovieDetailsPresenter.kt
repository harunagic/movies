package com.movies.app.ui.movie_details

import com.movies.app.data.repository.MovieRepository
import com.movies.app.misc.flatMapToViewState
import com.movies.app.ui.base.BasePresenter
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

    subscribeForStateUpdates(
        mergeResults(moviesObs),
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
  }
}
