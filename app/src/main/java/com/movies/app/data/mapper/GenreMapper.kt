package com.movies.app.data.mapper

import com.movies.app.data.api.model.Genre
import com.movies.app.data.db.entity.GenreEntity
import com.movies.app.data.db.entity.LanguageEntity
import javax.inject.Inject

class GenreMapper @Inject constructor() {

  fun toEntities(data: List<Genre>?) = data?.map { toEntity(it) } as ArrayList<GenreEntity>?

  fun toEntity(data: Genre) = GenreEntity(
      id = data.id,
      name = data.name
  )

  fun toModels(data: List<GenreEntity>?) = data?.map { toModel(it) } as ArrayList<Genre>?

  fun toModel(data: GenreEntity) = Genre(
      id = data.id,
      name = data.name
  )
}