package com.movies.app.ui.movie.adapter

import android.view.ViewGroup
import com.movies.app.common.holder.LoadingViewHolder
import com.movies.app.common.model.Loading
import com.movies.app.common.model.Model
import com.movies.app.data.api.model.Movie
import com.movies.app.ui.base.BaseAdapter
import com.movies.app.ui.base.BaseViewHolder
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MovieAdapter @Inject constructor() : BaseAdapter<Model<*>, BaseViewHolder<Model<*>, *>>(), MovieAdapterView {

  override val movieClicked: PublishSubject<Movie> = PublishSubject.create()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): BaseViewHolder<Model<*>, *> = when (viewType) {
    0 -> LoadingViewHolder(parent)
    else -> MovieViewHolder(parent, this)
  } as BaseViewHolder<Model<*>, *>

  override fun getItemViewType(position: Int): Int = when (items[position]) {
    is Loading -> 0
    else -> 1
  }
}