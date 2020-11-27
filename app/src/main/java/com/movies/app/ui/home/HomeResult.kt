package com.movies.app.ui.home

sealed class HomeResult(
  val loading: Boolean = false,
  val error: String? = null,
)

class LoadingPartialViewState(
  loading: Boolean
) : HomeResult(
    loading = loading
)

class ErrorPartialViewState(
  error: String?
) : HomeResult(
    error = error
)

