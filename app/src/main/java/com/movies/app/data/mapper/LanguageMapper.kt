package com.movies.app.data.mapper

import com.movies.app.data.api.model.Language
import com.movies.app.data.db.entity.LanguageEntity
import javax.inject.Inject

class LanguageMapper @Inject constructor() : BaseMapper<Language, LanguageEntity>() {

  override fun toEntity(data: Language) = LanguageEntity(
      iso = data.iso,
      name = data.name
  )

  override fun toModel(data: LanguageEntity) = Language(
      iso = data.iso,
      name = data.name
  )
}