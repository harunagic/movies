package com.movies.app.misc

import io.reactivex.Observable

fun <T, R, VS> Observable<T>.flatMapToViewState(
  source: (T) -> Observable<R>,
  mapSuccess: (R) -> VS,
  mapError: (Throwable) -> VS,
  mapLoading: ((T) -> VS)? = null
): Observable<VS> {
  return this.switchMap { value ->
    Observable.just(value)
        .flatMap { it ->
          var observable = Observable.just(it)
              .flatMap { source.invoke(it) }
              .map { mapSuccess.invoke(it) }
              .doOnError { mapError.invoke(it) }
              .onErrorReturn { mapError.invoke(it) }
          if (mapLoading != null) {
            observable = observable.startWith(mapLoading.invoke(it))
          }
          observable
        }
  }
}

fun <T> Observable<T>.switchNavigationState(
  navigationStateCheck: (T) -> Boolean,
  resetState: (T) -> T
): Observable<T> {
  return this.flatMap {
    if (navigationStateCheck.invoke(it)) {
      Observable.fromIterable(listOf(it, resetState.invoke(it)))
    } else {
      Observable.just(it)
    }
  }
}