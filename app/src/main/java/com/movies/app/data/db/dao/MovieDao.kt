package com.movies.app.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.movies.app.data.db.entity.MovieEntity
import io.reactivex.Observable

@Dao
interface MovieDao {

  @Query("SELECT * from movies")
  fun get(): Observable<List<MovieEntity>>

  @Query("SELECT * from movies WHERE id == :id")
  fun get(id: Int): Observable<MovieEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(data: List<MovieEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(data: MovieEntity)

  @Update
  fun update(data: MovieEntity)

  @Delete
  fun delete(data: MovieEntity)

  @Query("DELETE FROM movies")
  fun deleteAll()
}