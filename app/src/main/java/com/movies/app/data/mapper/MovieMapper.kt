package com.movies.app.data.mapper

import com.movies.app.data.api.model.Movie
import com.movies.app.data.db.entity.MovieEntity
import javax.inject.Inject

class MovieMapper @Inject constructor(
  private val genreMapper: GenreMapper,
  private val languageMapper: LanguageMapper
) : BaseMapper<Movie, MovieEntity>() {

  override fun toEntity(data: Movie) = MovieEntity(
      id = data.id,
      title = data.title,
      tagline = data.tagline,
      poster = data.poster,
      backdrop = data.backdrop,
      runtime = data.runtime,
      overview = data.overview,
      releaseDate = data.releaseDate,
      genres = genreMapper.toEntities(data.genres),
      languages = languageMapper.toEntities(data.languages),
      voteAverage = data.voteAverage
  )

  override fun toModel(data: MovieEntity) = Movie(
      id = data.id,
      title = data.title,
      tagline = data.tagline,
      poster = data.poster,
      backdrop = data.backdrop,
      runtime = data.runtime,
      overview = data.overview,
      releaseDate = data.releaseDate,
      genres = genreMapper.toModels(data.genres),
      languages = languageMapper.toModels(data.languages),
      voteAverage = data.voteAverage
  )
}