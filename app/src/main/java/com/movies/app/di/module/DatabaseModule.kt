package com.movies.app.di.module

import androidx.room.Room
import com.movies.app.App
import com.movies.app.data.db.MovieDatabase
import com.movies.app.data.db.dao.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule constructor(private val app: App) {

  @Singleton
  @Provides
  fun providesMovieDatabase(): MovieDatabase =
    Room.databaseBuilder(app, MovieDatabase::class.java, "movies-db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

  @Provides
  @Singleton
  fun providesMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()
}