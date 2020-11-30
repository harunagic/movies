package com.movies.app.ui.splash

import com.movies.app.ui.base.BasePresenter
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Inject

class SplashPresenter @Inject constructor() : BasePresenter<SplashView, SplashResult, SplashUiState>() {

  override fun initialState(): SplashUiState = SplashUiState()

  override fun bind() {
    val animateObs = intent(SplashView::onCreated)
        .map { AnimateSplashResult() }

    val finishObs = intent(SplashView::onCreated)
        .delay(1, SECONDS)
        .map { FinishSplashResult() }

    subscribeForStateUpdates(
        mergeResults(animateObs, finishObs),
        SplashView::update
    )
  }

  override fun reduce(
    state: SplashUiState,
    result: SplashResult
  ): SplashUiState =
    when (result) {
      is AnimateSplashResult -> state.copy(
          animateLogo = true
      )
      is FinishSplashResult -> state.copy(
          finishSplash = true,
          animateLogo = false
      )
    }
}
