package com.movies.app.di.component

import com.movies.app.App
import com.movies.app.di.module.ApiModule
import com.movies.app.di.module.AppModule
import com.movies.app.di.module.DatabaseModule
import com.movies.app.ui.movie.MovieFragment
import com.movies.app.ui.movie_details.MovieDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
      AppModule::class,
      ApiModule::class,
      DatabaseModule::class
    ]
)
interface AppComponent {
  fun inject(app: App)
  fun inject(fragment: MovieFragment)
  fun inject(fragment: MovieDetailsFragment)
}