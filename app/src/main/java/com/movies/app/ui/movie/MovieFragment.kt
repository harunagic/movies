package com.movies.app.ui.movie

import android.widget.Toast
import androidx.lifecycle.Lifecycle.Event
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding3.widget.textChanges
import com.movies.app.R
import com.movies.app.common.listener.LoadMoreScrollListener
import com.movies.app.data.api.model.Movie
import com.movies.app.di.component.AppComponent
import com.movies.app.ui.base.BaseFragment
import com.movies.app.ui.movie.adapter.MovieAdapter
import com.movies.app.ui.movie.adapter.MovieItemDecorator
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Observable
import kotlinx.android.synthetic.main.movie_fragment.inputSearch
import kotlinx.android.synthetic.main.movie_fragment.listMovies
import javax.inject.Inject

class MovieFragment : BaseFragment(R.layout.movie_fragment), MovieView {

  @Inject override lateinit var presenter: MoviePresenter
  @Inject lateinit var loadMoreScrollListener: LoadMoreScrollListener
  @Inject lateinit var movieAdapter: MovieAdapter

  override fun inject(appComponent: AppComponent) {
    appComponent.inject(this)
  }

  override fun onCreated(): Observable<Event> = RxLifecycle.onStart(this)

  override fun loadMore(): Observable<Pair<Int, String>> = loadMoreScrollListener.loadMoreSubject
      .map { Pair(it, inputSearch.text.toString()) }

  override fun onSearchQuery(): Observable<String> = inputSearch.textChanges()
      .map { it.toString() }

  override fun movieClicked(): Observable<Movie> = movieAdapter.movieClicked

  override fun setupUI() {
    listMovies.addItemDecoration(MovieItemDecorator())
    listMovies.addOnScrollListener(loadMoreScrollListener)
    listMovies.adapter = movieAdapter
  }

  override fun update(uiState: MovieUIState) {
    // Movies state
    uiState.movies?.let {
      movieAdapter.items = it
    }

    // Next page state
    loadMoreScrollListener.nextPage = uiState.nextPage

    // Movie details state
    uiState.movieDetails?.let {
      findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(it.id))
    }

    // Error state
    uiState.error?.let {
      Toast.makeText(context, it, Toast.LENGTH_SHORT)
          .show()
    }
  }
}