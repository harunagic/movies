package com.movies.app.ui.common.utils

import androidx.recyclerview.widget.DiffUtil.Callback
import com.movies.app.ui.common.model.Model

class ModelDiffUtilCallback(
  private val oldList: List<Model<*>>,
  private val newList: List<Model<*>>
) : Callback() {

  override fun getOldListSize(): Int = oldList.size

  override fun getNewListSize(): Int = newList.size

  override fun areItemsTheSame(
    oldItemPosition: Int,
    newItemPosition: Int
  ): Boolean = oldList[oldItemPosition].id() == newList[newItemPosition].id()

  override fun areContentsTheSame(
    oldItemPosition: Int,
    newItemPosition: Int
  ): Boolean = oldList[oldItemPosition] == newList[newItemPosition]
}