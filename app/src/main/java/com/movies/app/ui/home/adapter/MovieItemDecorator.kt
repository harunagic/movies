package com.movies.app.ui.home.adapter

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
    outRect.left = parent.resources.getDimensionPixelOffset(R.dimen.standard_half)
    outRect.right = parent.resources.getDimensionPixelOffset(R.dimen.standard_half)
    outRect.bottom = parent.resources.getDimensionPixelOffset(R.dimen.standard_half)
    if (position == 0) {
      outRect.top = parent.resources.getDimensionPixelOffset(R.dimen.standard)
    } else if (position == itemCount - 1) {
      outRect.bottom = parent.resources.getDimensionPixelOffset(R.dimen.standard)
    }
  }
}