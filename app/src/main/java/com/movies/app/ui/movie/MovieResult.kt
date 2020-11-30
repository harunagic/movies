package com.movies.app.ui.movie

import com.movies.app.common.model.Model
import com.movies.app.data.api.model.Movie
import com.movies.app.data.api.model.response.MovieResponse

sealed class MovieResult(
  val error: String? = null,
  val movies: List<Model<Int>> = listOf(),
  val nextPage: Int = 2,
  val movieDetails: Movie? = null
)

class LoadingPartialViewState : MovieResult()

class ErrorPartialViewState(
  error: String?
) : MovieResult(
    error = error
)

class MoviesPartialViewState(
  movies: MovieResponse
) : MovieResult(
    movies = movies.results ?: listOf(),
    nextPage = movies.page?.plus(1) ?: 0
)

class MovieDetailsPartialViewState(
  movieDetails: Movie? = null
) : MovieResult(
    movieDetails = movieDetails
)