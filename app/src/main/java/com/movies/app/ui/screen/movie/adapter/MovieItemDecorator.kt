package com.movies.app.ui.screen.movie.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State
import com.movies.app.R

class MovieItemDecorator : RecyclerView.ItemDecoration() {

  override fun getItemOffsets(
    outRect: Rect,
    view: View,
    parent: RecyclerView,
    state: State
  ) {
    val position = parent.getChildAdapterPosition(view)
    val itemCount = parent.adapter?.itemCount ?: 0
    if (position == 0) {
      outRect.top = parent.resources.getDimensionPixelOffset(R.dimen.standard_half)
    } else if (position == itemCount - 1) {
      outRect.bottom = parent.resources.getDimensionPixelOffset(R.dimen.standard_half)
    }
  }
}