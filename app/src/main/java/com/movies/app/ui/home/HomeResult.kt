package com.movies.app.ui.home

import com.movies.app.data.api.model.Movie

sealed class HomeResult(
  val loading: Boolean = false,
  val error: String? = null,
  val movies: List<Movie>? = null,
)

class LoadingPartialViewState(
  loading: Boolean
) : HomeResult(
    loading = loading
)

class ErrorPartialViewState(
  error: String?
) : HomeResult(
    error = error
)

class MoviesPartialViewState(
  movies: List<Movie>? = null
) : HomeResult(
    movies = movies
)