package com.movies.app.data.api.model.response

import com.google.gson.annotations.SerializedName
import com.movies.app.data.api.model.Movie

data class MovieResponse(
  @SerializedName("total_results")
  val totalResults: Int? = null,
  @SerializedName("page")
  val page: Int? = null,
  @SerializedName("total_pages")
  val totalPages: Int? = null,
  @SerializedName("results")
  val results: List<Movie>? = null,
)