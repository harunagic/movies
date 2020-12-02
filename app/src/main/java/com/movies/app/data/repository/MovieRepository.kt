package com.movies.app.data.repository

import com.movies.app.data.api.ApiService
import com.movies.app.data.api.model.Movie
import com.movies.app.data.api.model.response.MovieResponse
import com.movies.app.data.db.dao.MovieDao
import com.movies.app.data.mapper.MovieMapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieRepository @Inject constructor(
  private val apiService: ApiService,
  private val movieDao: MovieDao,
  private val movieMapper: MovieMapper
) : BaseRepository() {

  /**
   * @param forceRemote if true list of movies will be loaded from API
   * @return list of movies
   */
  fun getMovies(
    forceRemote: Boolean = false,
    page: Int = 1
  ): Observable<MovieResponse> = when {
    forceRemote -> getMoviesFromApi(page)
    else -> getMoviesFromDatabase(page)
  }.subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())

  /**
   * @return list of movies from API
   */
  private fun getMoviesFromApi(
    page: Int
  ): Observable<MovieResponse> = apiService.getMovies(page)
      .doOnNext { movieDao.insert(movieMapper.toEntities(it.results)) }

  /**
   * @return list of movies from local database, if list is empty then try to fetch from API
   */
  private fun getMoviesFromDatabase(
    page: Int
  ): Observable<MovieResponse> = movieDao.get()
      .map { movieMapper.toModels(it) }
      .map { MovieResponse(results = it) }
      .flatMap {
        if (it.results.isNullOrEmpty()) getMoviesFromApi(page)
        else Observable.just(it)
      }

  /**
   * @param query word or search term that we use to find movies
   * @return list of movies
   */
  fun search(
    page: Int = 1,
    query: String
  ): Observable<MovieResponse> = apiService.search(page, query)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())

  /**
   * @param forceRemote if true movie will be loaded from API
   * @return movie by id
   */
  fun getMovieById(
    forceRemote: Boolean = false,
    id: Int
  ): Observable<Movie> = when {
    forceRemote -> getMovieByIdFromApi(id)
    else -> getMovieByIdFromDatabase(id)
  }.subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())

  /**
   * @return movie by id from API
   */
  private fun getMovieByIdFromApi(id: Int): Observable<Movie> = apiService.getMovieById(id)
      .doOnNext { movieDao.insert(movieMapper.toEntity(it)) }

  /**
   * @return movie by id from local database, if list is empty then try to fetch from API
   */
  private fun getMovieByIdFromDatabase(id: Int): Observable<Movie> = movieDao.get(id)
      .map { movieMapper.toModel(it) }
      .onErrorResumeNext(getMovieByIdFromApi(id))

}