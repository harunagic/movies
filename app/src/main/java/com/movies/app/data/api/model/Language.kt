package com.movies.app.data.api.model

import com.google.gson.annotations.SerializedName

data class Language(
  @SerializedName("iso_639_1")
  val iso: String,
  @SerializedName("name")
  val name: String?,
)