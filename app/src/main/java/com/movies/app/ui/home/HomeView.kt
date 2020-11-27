package com.movies.app.ui.home

import androidx.lifecycle.Lifecycle
import com.movies.app.ui.base.BaseView
import io.reactivex.Observable

interface HomeView : BaseView {
  fun onCreated(): Observable<Lifecycle.Event>
  fun update(uiState: HomeUIState)
}