package com.movies.app.data.api

import com.movies.app.data.api.model.Movie
import com.movies.app.data.api.model.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

  @GET("discover/movie")
  fun getMovies(): Observable<MovieResponse>

  @GET("movie/{id}")
  fun getMovieById(@Path("id") id: String): Observable<Movie>
}