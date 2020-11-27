package com.movies.app.ui.home

import com.movies.app.data.api.model.Movie

data class HomeUIState(
  val loading: Boolean = false,
  val error: String? = null,
  val movies: List<Movie>? = null,
)