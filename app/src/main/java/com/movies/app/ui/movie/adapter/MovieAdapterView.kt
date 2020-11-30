package com.movies.app.ui.movie.adapter

import com.movies.app.data.api.model.Movie
import io.reactivex.subjects.PublishSubject

interface MovieAdapterView {
  val movieClicked: PublishSubject<Movie>
}