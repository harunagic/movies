package com.movies.app.ui.movie.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.movies.app.data.api.model.Movie
import com.movies.app.ui.base.BaseListViewHolder
import kotlinx.android.synthetic.main.movie_list_item.view.imgPoster
import kotlinx.android.synthetic.main.movie_list_item.view.txtOverview
import kotlinx.android.synthetic.main.movie_list_item.view.txtTitle
import kotlinx.android.synthetic.main.movie_list_item.view.txtReleaseDate
import java.text.SimpleDateFormat
import java.util.Locale

class MovieViewHolder(
  private val itemView: View,
  private val callback: MovieCallback
) : BaseListViewHolder<Movie>(itemView) {

  private val currentDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
  private val newDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

  override fun bind(item: Movie) {
    // Title
    item.title?.let {
      itemView.txtTitle.text = it
    }

    // Release date
    item.releaseDate?.let {
      currentDateFormat.parse(it)?.let { date->
        itemView.txtReleaseDate.text = newDateFormat.format(date)
      }
    }

    // Overview
    item.overview?.let {
      itemView.txtOverview.text = it
    }

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