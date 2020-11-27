package com.movies.app.ui.home

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle.Event
import com.movies.app.R
import com.movies.app.data.api.model.Movie
import com.movies.app.di.component.AppComponent
import com.movies.app.ui.base.BaseFragment
import com.movies.app.ui.home.adapter.MovieAdapter
import com.movies.app.ui.home.adapter.MovieCallback
import com.movies.app.ui.home.adapter.MovieItemDecorator
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Observable
import kotlinx.android.synthetic.main.home_fragment.listMovies
import kotlinx.android.synthetic.main.home_fragment.loader
import javax.inject.Inject

class HomeFragment : BaseFragment(R.layout.home_fragment), HomeView {

  @Inject override lateinit var presenter: HomePresenter
  @Inject lateinit var movieAdapter: MovieAdapter

  override fun inject(appComponent: AppComponent) {
    appComponent.inject(this)
  }

  override fun onCreated(): Observable<Event> = RxLifecycle.onStart(this)

  override fun setupUI() {
    listMovies.addItemDecoration(MovieItemDecorator())
    listMovies.adapter = movieAdapter
  }

  override fun setupListeners() {
    movieAdapter.callback = object : MovieCallback {
      override fun onMovieClicked(item: Movie) {
        //todo navigate to details screen
      }
    }
  }

  override fun update(uiState: HomeUIState) {
    // Loading state
    loader.isVisible = uiState.loading

    // Movies state
    uiState.movies?.let {
      movieAdapter.setItems(it)
    }

    // Error state
    uiState.error?.let {
      Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }
  }
}