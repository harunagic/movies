package com.movies.app.ui.base.mvi

import com.movies.app.ui.base.BaseView

interface Presenter {
  fun attachView(view: BaseView)
  fun detachView()
  fun destroy()
}