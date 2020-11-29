package com.movies.app.data.mapper

import com.movies.app.data.api.model.Language
import com.movies.app.data.db.entity.LanguageEntity
import javax.inject.Inject

class LanguageMapper @Inject constructor() {

  fun toEntities(data: List<Language>?) = data?.map { toEntity(it) } as ArrayList<LanguageEntity>?

  fun toEntity(data: Language) = LanguageEntity(
      iso = data.iso,
      name = data.name
  )

  fun toModels(data: List<LanguageEntity>?) = data?.map { toModel(it) } as ArrayList<Language>?

  fun toModel(data: LanguageEntity) = Language(
      iso = data.iso,
      name = data.name
  )
}