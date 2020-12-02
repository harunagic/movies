package com.movies.app.data.api.model

import com.google.gson.annotations.SerializedName
import com.movies.app.ui.common.model.Model

data class Movie(
  @SerializedName("id")
  val id: Int,
  @SerializedName("title")
  val title: String?,
  @SerializedName("tagline")
  val tagline: String?,
  @SerializedName("poster_path")
  val poster: String?,
  @SerializedName("backdrop_path")
  val backdrop: String?,
  @SerializedName("runtime")
  val runtime: String?,
  @SerializedName("overview")
  val overview: String?,
  @SerializedName("release_date")
  val releaseDate: String?,
  @SerializedName("genres")
  val genres: ArrayList<Genre>?,
  @SerializedName("spoken_languages")
  val languages: ArrayList<Language>?
) : Model<Int> {
  override fun id(): Int = id
}