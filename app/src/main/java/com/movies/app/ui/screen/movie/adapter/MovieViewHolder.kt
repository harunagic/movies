package com.movies.app.ui.screen.movie.adapter

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jakewharton.rxbinding3.view.clicks
import com.movies.app.R
import com.movies.app.data.api.model.Movie
import com.movies.app.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.movie_list_item.view.imgPoster
import kotlinx.android.synthetic.main.movie_list_item.view.rbVote
import kotlinx.android.synthetic.main.movie_list_item.view.txtOverview
import kotlinx.android.synthetic.main.movie_list_item.view.txtReleaseDate
import kotlinx.android.synthetic.main.movie_list_item.view.txtTitle
import java.text.SimpleDateFormat
import java.util.Locale

class MovieViewHolder(
  parent: ViewGroup,
  adapterView: MovieAdapterView
) : BaseViewHolder<Movie, MovieAdapterView>(parent, R.layout.movie_list_item, adapterView) {

  private val currentDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
  private val newDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

  init {
    itemView.clicks()
        .map { data }
        .subscribe(adapterView.movieClicked)
  }

  override fun bind(data: Movie) {
    super.bind(data)

    // Title
    data.title?.let {
      itemView.txtTitle.text = it
    }

    // Release date
    if (!data.releaseDate.isNullOrBlank()) {
      currentDateFormat.parse(data.releaseDate)?.let { date->
        itemView.txtReleaseDate.text = newDateFormat.format(date)
      }
    }

    // Overview
    data.overview?.let {
      itemView.txtOverview.text = it
    }

    // Average vote
    data.voteAverage?.let {
      itemView.rbVote.progress = it.toInt()
    }

    // Poster
    Glide.with(itemView)
        .load("https://image.tmdb.org/t/p/w500/${data.poster}")
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(itemView.imgPoster)
  }
}