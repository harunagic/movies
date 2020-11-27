package com.movies.app.ui.base.mvi

import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.Subject

internal class DisposableIntentObserver<I>(private val subject: Subject<I>) : DisposableObserver<I>() {

  override fun onNext(value: I) {
    subject.onNext(value)
  }

  override fun onError(e: Throwable) {
    throw IllegalStateException("View intents must not throw errors", e)
  }

  override fun onComplete() {
    subject.onComplete()
  }
}
