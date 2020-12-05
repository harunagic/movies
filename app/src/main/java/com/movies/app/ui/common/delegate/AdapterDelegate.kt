package com.movies.app.ui.common.delegate

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.movies.app.ui.common.model.Model
import com.movies.app.ui.common.utils.ModelDiffUtilCallback
import kotlin.reflect.KProperty

class AdapterItemsDelegate<T : Model<*>>(private var value: List<T> = ArrayList()) {

  operator fun getValue(
    thisRef: RecyclerView.Adapter<*>,
    property: KProperty<*>
  ): List<T> {
    return this.value
  }

  operator fun setValue(
    thisRef: RecyclerView.Adapter<*>,
    property: KProperty<*>,
    value: List<T>
  ) {
    val result = DiffUtil.calculateDiff(ModelDiffUtilCallback(this.value, value))
    this.value = value
    result.dispatchUpdatesTo(thisRef)
  }
}