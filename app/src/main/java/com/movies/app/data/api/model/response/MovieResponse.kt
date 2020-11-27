package com.movies.app.data.api.model.response

import com.google.gson.annotations.SerializedName
import com.movies.app.data.api.model.Movie

data class MovieResponse(
  @SerializedName("total_results")
  val totalResults: Int,
  @SerializedName("page")
  val page: Int,
  @SerializedName("total_pages")
  val totalPages: Int,
  @SerializedName("results")
  val results: List<Movie>,
)