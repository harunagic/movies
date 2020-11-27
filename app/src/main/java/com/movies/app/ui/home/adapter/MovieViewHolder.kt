package com.movies.app.ui.home.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.movies.app.data.api.model.Movie
import com.movies.app.ui.base.BaseListViewHolder
import kotlinx.android.synthetic.main.movie_list_item.view.imgPoster
import kotlinx.android.synthetic.main.movie_list_item.view.txtOverview
import kotlinx.android.synthetic.main.movie_list_item.view.txtTitle

class MovieViewHolder(
  private val itemView: View,
  private val callback: MovieCallback
) : BaseListViewHolder<Movie>(itemView) {

  override fun bind(item: Movie) {
    // Title
    itemView.txtTitle.text = item.title

    // Runtime
    itemView.txtOverview.text = item.overview

    // Poster
    Glide.with(itemView)
        .load("https://image.tmdb.org/t/p/w500/${item.poster}")
        .transition(withCrossFade())
        .into(itemView.imgPoster)
  }

  override fun attachListeners(item: Movie) {
    itemView.setOnClickListener { callback.onMovieClicked(item) }
  }
}