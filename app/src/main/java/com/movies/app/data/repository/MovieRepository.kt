package com.movies.app.data.repository

import com.movies.app.data.api.ApiService
import com.movies.app.data.api.model.Movie
import com.movies.app.data.db.dao.MovieDao
import com.movies.app.data.mapper.MovieMapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MovieRepository @Inject constructor(
  private val apiService: ApiService,
  private val movieDao: MovieDao,
  private val movieMapper: MovieMapper
) {

  /**
   * @param forceRemote if true list of movies will be loaded from API
   * @return list of movies
   */
  fun getMovies(
    forceRemote: Boolean = false
  ): Observable<List<Movie>> = when {
    forceRemote -> getMoviesFromApi()
    else -> getFromDatabase()
  }.subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())

  /**
   * @return list of movies from API
   */
  private fun getMoviesFromApi(): Observable<List<Movie>> = apiService.getMovies()
      .doOnNext { movieDao.insert(movieMapper.toEntities(it.results)) }
      .doOnNext { Timber.d("Data loaded from API!") }
      .map { it.results }

  /**
   * @return list of movies from local database, if list is empty then try to fetch from API
   */
  private fun getFromDatabase(): Observable<List<Movie>> = movieDao.get()
      .map { movieMapper.toModels(it) }
      .doOnNext { Timber.d("Data loaded from local database!") }
      .flatMap {
        if (it.isEmpty()) getMoviesFromApi()
        else Observable.just(it)
      }
}