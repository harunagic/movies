package com.movies.app.ui.home

data class HomeUIState(
  val loading: Boolean = false,
  val error: String? = null,
)