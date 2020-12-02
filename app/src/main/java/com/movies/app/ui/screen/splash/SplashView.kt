package com.movies.app.ui.screen.splash

import androidx.lifecycle.Lifecycle.Event
import com.movies.app.ui.base.BaseView
import io.reactivex.Observable

interface SplashView : BaseView {
  fun onCreated(): Observable<Event>
  fun update(uiState: SplashUiState)
}
