package com.movies.app.ui.movie_details

import com.movies.app.data.api.model.Movie

data class MovieDetailsUIState(
  val loading: Boolean = false,
  val error: String? = null,
  val movie: Movie? = null,
)