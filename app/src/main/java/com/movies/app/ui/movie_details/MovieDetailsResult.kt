package com.movies.app.ui.movie_details

import com.movies.app.data.api.model.Movie

sealed class MovieDetailsResult(
  val loading: Boolean = false,
  val error: String? = null,
  val movie: Movie? = null,
)

class LoadingPartialViewState(
  loading: Boolean
) : MovieDetailsResult(
    loading = loading
)

class ErrorPartialViewState(
  error: String?
) : MovieDetailsResult(
    error = error
)

class MoviePartialViewState(
  movie: Movie? = null
) : MovieDetailsResult(
    movie = movie
)