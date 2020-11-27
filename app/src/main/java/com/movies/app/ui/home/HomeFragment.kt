package com.movies.app.ui.home

import androidx.lifecycle.Lifecycle.Event
import com.movies.app.R
import com.movies.app.di.component.AppComponent
import com.movies.app.ui.base.BaseFragment
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Observable
import javax.inject.Inject

class HomeFragment : BaseFragment(R.layout.home_fragment), HomeView {

  @Inject override lateinit var presenter: HomePresenter

  override fun inject(appComponent: AppComponent) {
    appComponent.inject(this)
  }

  override fun onCreated(): Observable<Event> = RxLifecycle.onStart(this)

  override fun update(uiState: HomeUIState) {
    //todo
  }
}