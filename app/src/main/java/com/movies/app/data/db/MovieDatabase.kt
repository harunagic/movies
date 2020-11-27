package com.movies.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.movies.app.data.db.dao.MovieDao
import com.movies.app.data.db.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
}