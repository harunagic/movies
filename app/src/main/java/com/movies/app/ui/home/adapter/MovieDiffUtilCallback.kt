package com.movies.app.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.movies.app.data.api.model.Movie

class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
  override fun areItemsTheSame(
    oldItem: Movie,
    newItem: Movie
  ): Boolean = oldItem.id == newItem.id

  override fun areContentsTheSame(
    oldItem: Movie,
    newItem: Movie
  ): Boolean = oldItem == newItem
}