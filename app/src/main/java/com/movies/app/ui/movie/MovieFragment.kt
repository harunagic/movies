package com.movies.app.ui.movie

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle.Event
import androidx.navigation.fragment.findNavController
import com.movies.app.R
import com.movies.app.data.api.model.Movie
import com.movies.app.di.component.AppComponent
import com.movies.app.ui.base.BaseFragment
import com.movies.app.ui.movie.adapter.MovieAdapter
import com.movies.app.ui.movie.adapter.MovieCallback
import com.movies.app.ui.movie.adapter.MovieItemDecorator
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Observable
import kotlinx.android.synthetic.main.movie_fragment.listMovies
import kotlinx.android.synthetic.main.movie_fragment.loader
import javax.inject.Inject

class MovieFragment : BaseFragment(R.layout.movie_fragment), MovieView {

  @Inject override lateinit var presenter: MoviePresenter
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
        findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(item.id))
      }
    }
  }

  override fun update(uiState: MovieUIState) {
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