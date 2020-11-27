@file:Suppress("UNCHECKED_CAST")

package com.movies.app.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<M>(
  diffUtilCallback: DiffUtil.ItemCallback<M>
) : ListAdapter<M, BaseListViewHolder<M>>(diffUtilCallback) {

  abstract fun getViewModel(
    inflater: LayoutInflater,
    parent: ViewGroup,
    viewType: Int
  ): BaseListViewHolder<M>

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): BaseListViewHolder<M> {
    val inflater = LayoutInflater.from(parent.context)
    return getViewModel(inflater, parent, viewType)
  }

  override fun onBindViewHolder(
    holder: BaseListViewHolder<M>,
    position: Int
  ) {
    holder.bind(getItem(position))
    holder.attachListeners(getItem(position))
  }

  fun setItems(items: List<M>) {
    submitList(items)
  }
}