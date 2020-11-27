package com.movies.app.di.component

import com.movies.app.App
import com.movies.app.di.module.ApiModule
import com.movies.app.di.module.AppModule
import com.movies.app.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
      AppModule::class,
      ApiModule::class
    ]
)
interface AppComponent {
  fun inject(app: App)
  fun inject(fragment: HomeFragment)
}