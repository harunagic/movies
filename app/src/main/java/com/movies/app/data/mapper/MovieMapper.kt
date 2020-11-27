package com.movies.app.data.mapper

import com.movies.app.data.api.model.Movie
import com.movies.app.data.db.entity.MovieEntity
import javax.inject.Inject

class MovieMapper @Inject constructor() {

  fun toEntities(data: List<Movie>) = data.map { toEntity(it) }

  fun toEntity(data: Movie) = MovieEntity(
      id = data.id,
      name = data.title,
      poster = data.poster,
      runtime = data.runtime,
      overview = data.overview
  )

  fun toModels(data: List<MovieEntity>) = data.map { toModel(it) }

  fun toModel(data: MovieEntity) = Movie(
      id = data.id,
      title = data.name,
      poster = data.poster,
      runtime = data.runtime,
      overview = data.overview
  )
}