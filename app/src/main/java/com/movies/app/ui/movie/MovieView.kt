package com.movies.app.ui.movie

import androidx.lifecycle.Lifecycle
import com.movies.app.ui.base.BaseView
import io.reactivex.Observable

interface MovieView : BaseView {
  fun onCreated(): Observable<Lifecycle.Event>
  fun update(uiState: MovieUIState)
}