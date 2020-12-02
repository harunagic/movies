package com.movies.app.ui.base

import androidx.recyclerview.widget.RecyclerView
import com.movies.app.ui.common.delegate.AdapterItemsDelegate
import com.movies.app.ui.common.model.Model

abstract class BaseAdapter<T : Model<*>, VH : BaseViewHolder<T, *>> : RecyclerView.Adapter<VH>() {

  var items: List<T> by AdapterItemsDelegate()

  override fun onBindViewHolder(
    holder: VH,
    position: Int
  ) {
    holder.bind(items[position])
  }

  override fun getItemCount(): Int = items.size
}