package com.movies.app.ui.home

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle.Event
import com.movies.app.R
import com.movies.app.di.component.AppComponent
import com.movies.app.ui.base.BaseFragment
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Observable
import kotlinx.android.synthetic.main.home_fragment.loader
import javax.inject.Inject

class HomeFragment : BaseFragment(R.layout.home_fragment), HomeView {

  @Inject override lateinit var presenter: HomePresenter

  override fun inject(appComponent: AppComponent) {
    appComponent.inject(this)
  }

  override fun onCreated(): Observable<Event> = RxLifecycle.onStart(this)

  override fun update(uiState: HomeUIState) {
    // Loading state
    loader.isVisible = uiState.loading

    // Movies state
    uiState.movies?.let {
      //todo show list of movies
      Toast.makeText(context, it.size.toString(), Toast.LENGTH_SHORT).show()
    }

    // Error state
    uiState.error?.let {
      Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }
  }
}