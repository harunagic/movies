package com.movies.app.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.movies.app.R
import com.movies.app.data.api.model.Movie
import com.movies.app.ui.base.BaseListAdapter
import com.movies.app.ui.base.BaseListViewHolder
import javax.inject.Inject

class MovieAdapter @Inject constructor() : BaseListAdapter<Movie>(MovieDiffUtilCallback()) {

  lateinit var callback: MovieCallback

  override fun getViewModel(
    inflater: LayoutInflater,
    parent: ViewGroup,
    viewType: Int
  ): BaseListViewHolder<Movie> = MovieViewHolder(inflater.inflate(R.layout.movie_list_item, parent, false), callback)
}

