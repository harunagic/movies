package com.movies.app.ui.home

import com.movies.app.ui.base.BasePresenter
import javax.inject.Inject

class HomePresenter @Inject constructor() : BasePresenter<HomeView, HomeResult, HomeUIState>() {

  override fun initialState(): HomeUIState = HomeUIState()

  override fun bind() {
    //todo
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
  }
}
