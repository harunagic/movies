package com.movies.app.ui.screen.movie_details

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.chip.Chip
import com.jakewharton.rxbinding3.view.clicks
import com.movies.app.R
import com.movies.app.data.api.model.Movie
import com.movies.app.di.component.AppComponent
import com.movies.app.ui.base.BaseFragment
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Observable
import kotlinx.android.synthetic.main.movie_details_fragment.*
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment(R.layout.movie_details_fragment), MovieDetailsView {

  private val currentDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
  private val newDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
  private val args: MovieDetailsFragmentArgs by navArgs()

  @Inject override lateinit var presenter: MovieDetailsPresenter

  override fun inject(appComponent: AppComponent) {
    appComponent.inject(this)
  }

  override fun onCreated(): Observable<Int> = RxLifecycle.onStart(this)
      .map { args.id }

  override fun backClicked(): Observable<Unit> = btnBack.clicks()

  override fun update(uiState: MovieDetailsUIState) {
    // Loading state
    loader.isVisible = uiState.loading

    // Movie details state
    uiState.movie?.let {
      showMovieInfo(it)
    }

    // Back btn state
    if (uiState.back) {
      findNavController().popBackStack()
    }

    // Error state
    uiState.error?.let {
      Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }
  }

  /**
   * Show movie info
   */
  private fun showMovieInfo(movie: Movie) {
    // Title
    txtTitle.isVisible = !movie.title.isNullOrBlank()
    movie.title?.let {
      txtTitle.text = it
    }

    // Release date
    txtReleaseDate.isVisible = !movie.releaseDate.isNullOrBlank()
    movie.releaseDate?.let {
      currentDateFormat.parse(it)?.let { date->
        txtReleaseDate.text = newDateFormat.format(date)
      }
    }

    // Runtime
    txtRuntime.isVisible = !movie.runtime.isNullOrBlank()
    movie.runtime?.let {
      txtRuntime.text = "${it}min"
    }

    // Genres
    groupGenres.isVisible = !movie.genres.isNullOrEmpty()
    movie.genres?.forEach {
      val chip = Chip(context)
      chip.text = it.name
      groupGenres.addView(chip)
    }

    // Languages
    groupLanguages.isVisible = !movie.languages.isNullOrEmpty()
    movie.languages?.forEach {
      val chip = Chip(context)
      chip.text = it.name
      groupLanguages.addView(chip)
    }

    // Tagline
    txtTagline.isVisible = !movie.tagline.isNullOrBlank()
    movie.tagline?.let {
      txtTagline.text = it
    }

    // Overview
    txtOverview.isVisible = !movie.overview.isNullOrBlank()
    movie.overview?.let {
      txtOverview.text = it
    }

    // Backdrop
    Glide.with(this)
        .load("https://image.tmdb.org/t/p/w500/${movie.backdrop}")
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imgBackdrop)

    // Poster
    Glide.with(this)
        .load("https://image.tmdb.org/t/p/w500/${movie.poster}")
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imgPoster)
  }
}