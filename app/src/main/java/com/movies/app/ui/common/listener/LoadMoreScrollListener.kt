package com.movies.app.ui.common.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LoadMoreScrollListener @Inject constructor() : RecyclerView.OnScrollListener() {

  val loadMoreSubject: PublishSubject<Int> = PublishSubject.create()
  var nextPage: Int = 2

  override fun onScrolled(
    recyclerView: RecyclerView,
    dx: Int,
    dy: Int
  ) {
    super.onScrolled(recyclerView, dx, dy)
    val layoutManager = recyclerView.layoutManager
    if (dy > 0) {
      layoutManager?.let {
        val visibleItemCount = it.childCount
        val totalItemCount = it.itemCount
        val pastVisibleItems = when (it) {
          is LinearLayoutManager -> it.findFirstVisibleItemPosition()
          else -> 0
        }
        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
          loadMoreSubject.onNext(nextPage)
        }
      }
    }
  }
}