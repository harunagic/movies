package com.movies.app.ui.movie

import com.movies.app.common.model.Model
import com.movies.app.data.api.model.Movie

data class MovieUIState(
  val error: String? = null,
  val movies: List<Model<Int>>? = listOf(),
  val nextPage: Int = 2,
  val movieDetails: Movie? = null
)