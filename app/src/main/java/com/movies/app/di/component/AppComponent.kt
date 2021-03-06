package com.movies.app.di.component

import com.movies.app.App
import com.movies.app.di.module.ApiModule
import com.movies.app.di.module.AppModule
import com.movies.app.di.module.DatabaseModule
import com.movies.app.ui.screen.movie.MovieFragment
import com.movies.app.ui.screen.movie_details.MovieDetailsFragment
import com.movies.app.ui.screen.splash.SplashFragment
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
  fun inject(fragment: SplashFragment)
  fun inject(fragment: MovieFragment)
  fun inject(fragment: MovieDetailsFragment)
}