package com.movies.app.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
  @PrimaryKey
  val id: String,
  val name: String?,
  val poster: String?
)