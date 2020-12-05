package com.movies.app.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.movies.app.data.db.entity.MovieEntity
import io.reactivex.Observable

@Dao
interface MovieDao : BaseDao<MovieEntity> {

  @Query("SELECT * from movies")
  fun get(): Observable<List<MovieEntity>>

  @Query("SELECT * from movies WHERE id == :id")
  fun get(id: Int): Observable<MovieEntity>

  @Query("DELETE FROM movies")
  fun deleteAll()
}