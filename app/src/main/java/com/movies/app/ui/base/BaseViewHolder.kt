package com.movies.app.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : Any, AV : Any>(
  parent: ViewGroup,
  layoutId: Int,
  protected val adapterView: AV? = null
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

  lateinit var data: T

  @CallSuper
  open fun bind(data: T) {
    this.data = data
  }
}