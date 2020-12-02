package com.movies.app.ui.screen.movie_details

import com.movies.app.data.api.model.Movie

sealed class MovieDetailsResult(
  val loading: Boolean = false,
  val error: String? = null,
  val movie: Movie? = null,
  val back: Boolean = false,
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

class BackPartialViewState(
  back: Boolean
) : MovieDetailsResult(
    back = back
)