package com.movies.app.ui.home.adapter

import com.movies.app.data.api.model.Movie

interface MovieCallback {
  fun onMovieClicked(item: Movie) {}
}