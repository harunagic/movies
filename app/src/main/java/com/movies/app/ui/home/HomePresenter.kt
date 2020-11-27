package com.movies.app.ui.home

import com.movies.app.data.repository.MovieRepository
import com.movies.app.misc.flatMapToViewState
import com.movies.app.ui.base.BasePresenter
import javax.inject.Inject

class HomePresenter @Inject constructor(
  private val movieRepository: MovieRepository
) : BasePresenter<HomeView, HomeResult, HomeUIState>() {

  override fun initialState(): HomeUIState = HomeUIState()

  override fun bind() {
    val moviesObs = intent(HomeView::onCreated)
        .flatMapToViewState(
            { movieRepository.getMovies(forceRemote = true) },
            { MoviesPartialViewState(it) },
            { ErrorPartialViewState(it.localizedMessage) },
            { LoadingPartialViewState(true) }
        )

    subscribeForStateUpdates(
        mergeResults(moviesObs),
        HomeView::update
    )
  }

  override fun reduce(
    state: HomeUIState,
    result: HomeResult
  ): HomeUIState = when (result) {
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
