package com.movies.app

import android.app.Application
import com.movies.app.di.component.AppComponent
import com.movies.app.di.component.DaggerAppComponent
import com.movies.app.di.module.ApiModule
import com.movies.app.di.module.AppModule
import com.movies.app.di.module.DatabaseModule
import timber.log.Timber

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    setupLogging()
    setupDagger()
  }

  private fun setupLogging() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  private fun setupDagger() {
    appComponent = DaggerAppComponent.builder()
        .databaseModule(DatabaseModule(this))
        .apiModule(ApiModule(this))
        .appModule(AppModule(this))
        .build()
    appComponent.inject(this)
  }

  companion object {
    lateinit var appComponent: AppComponent
  }
}