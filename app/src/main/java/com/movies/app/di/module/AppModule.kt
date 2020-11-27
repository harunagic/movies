package com.movies.app.di.module

import com.movies.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

  @Provides
  @Singleton
  internal fun providesApp(): App = app
}