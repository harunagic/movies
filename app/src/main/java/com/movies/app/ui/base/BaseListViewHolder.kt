package com.movies.app.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseListViewHolder<M>(itemView: View) : RecyclerView.ViewHolder(itemView) {

  /**
   * Override in ViewHolder and process UI stuff that you need
   */
  open fun bind(item: M) {}

  /**
   * Override in ViewHolder and setup listeners
   */
  open fun attachListeners(item: M) {}
}