package com.movies.app.data.api

import com.movies.app.data.api.model.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

  @GET("discover/movie")
  fun getMovies(): Observable<MovieResponse>
}