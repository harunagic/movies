package com.movies.app.ui.movie.adapter

import com.movies.app.data.api.model.Movie

interface MovieCallback {
  fun onMovieClicked(item: Movie) {}
}