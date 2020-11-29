package com.movies.app.ui.movie

import com.movies.app.data.api.model.Movie

sealed class MovieResult(
  val loading: Boolean = false,
  val error: String? = null,
  val movies: List<Movie>? = null,
)

class LoadingPartialViewState(
  loading: Boolean
) : MovieResult(
    loading = loading
)

class ErrorPartialViewState(
  error: String?
) : MovieResult(
    error = error
)

class MoviesPartialViewState(
  movies: List<Movie>? = null
) : MovieResult(
    movies = movies
)