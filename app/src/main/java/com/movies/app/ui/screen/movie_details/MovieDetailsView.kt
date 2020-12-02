package com.movies.app.ui.screen.movie_details

import com.movies.app.ui.base.BaseView
import io.reactivex.Observable

interface MovieDetailsView : BaseView {
  fun onCreated(): Observable<Int>
  fun backClicked(): Observable<Unit>
  fun update(uiState: MovieDetailsUIState)
}