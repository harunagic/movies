package com.movies.app.data.api

import com.movies.app.data.api.model.Movie
import com.movies.app.data.api.model.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

  @GET("discover/movie")
  fun getMovies(@Query("page") page: Int): Observable<MovieResponse>

  @GET("movie/{id}")
  fun getMovieById(@Path("id") id: Int): Observable<Movie>

  @GET("search/movie")
  fun search(
    @Query("page") page: Int,
    @Query("query") query: String
  ): Observable<MovieResponse>
}