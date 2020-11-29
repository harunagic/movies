package com.movies.app.ui.movie

import com.movies.app.data.api.model.Movie

data class MovieUIState(
  val loading: Boolean = false,
  val error: String? = null,
  val movies: List<Movie>? = null,
)