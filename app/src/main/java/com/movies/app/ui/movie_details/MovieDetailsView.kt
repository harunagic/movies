package com.movies.app.ui.movie_details

import com.movies.app.ui.base.BaseView
import io.reactivex.Observable

interface MovieDetailsView : BaseView {
  fun onCreated(): Observable<Int>
  fun update(uiState: MovieDetailsUIState)
}