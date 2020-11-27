package com.movies.app.data.api.model

import com.google.gson.annotations.SerializedName

data class Movie(
  @SerializedName("id")
  val id: String,
  @SerializedName("name")
  val name: String?
)