package com.movies.app.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<Entity> {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(data: List<Entity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(data: Entity)

  @Update
  fun update(data: Entity)

  @Delete
  fun delete(data: Entity)
}