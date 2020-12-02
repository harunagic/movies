package com.movies.app.data.mapper

abstract class BaseMapper<Model, Entity> {

  /**
   * Converts list of Models to list of Entities
   */
  fun toEntities(data: List<Model>?) = when (data) {
    null -> arrayListOf()
    else -> data.map { toEntity(it) } as ArrayList<Entity>
  }

  /**
   * Converts list of Models to list of Entities
   */
  fun toModels(data: List<Entity>?) = when (data) {
    null -> arrayListOf()
    else -> data.map { toModel(it) } as ArrayList<Model>
  }

  /**
   * Converts Model > Entity
   */
  abstract fun toEntity(data: Model): Entity

  /**
   * Converts Entity > Model
   */
  abstract fun toModel(data: Entity): Model
}