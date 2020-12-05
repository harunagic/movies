package com.movies.app.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
  @PrimaryKey
  val id: Int,
  val title: String?,
  val tagline: String?,
  val poster: String?,
  val backdrop: String?,
  val runtime: String?,
  val overview: String?,
  val releaseDate: String?,
  @Embedded
  val genres: ArrayList<GenreEntity>?,
  @Embedded
  val languages: ArrayList<LanguageEntity>?,
  val voteAverage: Double? = 0.0
)