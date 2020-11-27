package com.movies.app.data.api.model

import com.google.gson.annotations.SerializedName

data class Movie(
  @SerializedName("id")
  val id: String,
  @SerializedName("title")
  val title: String?,
  @SerializedName("poster_path")
  val poster: String?,
  @SerializedName("runtime")
  val runtime: String?,
  @SerializedName("overview")
  val overview: String?
)