package com.movies.app.misc

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

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

fun <T> Observable<out T>.resetWith(
  value: T,
  duration: Long = 0,
  timeUnit: TimeUnit = TimeUnit.MILLISECONDS
): Observable<out T> {
  return this.flatMap {
    Observable
        .just(value)
        .delay(duration, timeUnit)
        .startWith(it)
  }
}

