package com.movies.app.data.mapper

import com.movies.app.data.api.model.Genre
import com.movies.app.data.db.entity.GenreEntity
import com.movies.app.data.db.entity.LanguageEntity
import javax.inject.Inject

class GenreMapper @Inject constructor() : BaseMapper<Genre, GenreEntity>() {

  override fun toEntity(data: Genre) = GenreEntity(
      id = data.id,
      name = data.name
  )

  override fun toModel(data: GenreEntity) = Genre(
      id = data.id,
      name = data.name
  )
}