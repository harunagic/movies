package com.movies.app.ui.base

import androidx.annotation.MainThread
import com.movies.app.ui.base.mvi.DisposableIntentObserver
import com.movies.app.ui.base.mvi.Presenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import io.reactivex.subjects.UnicastSubject
import timber.log.Timber

abstract class BasePresenter<View : BaseView, Result, State> : Presenter {

  private var stateBehaviorSubject: BehaviorSubject<State> = BehaviorSubject.create()
  private var stateDisposable: CompositeDisposable = CompositeDisposable()
  private var shortLivedDisposable: CompositeDisposable = CompositeDisposable()
  private var intentBinders: ArrayList<Binder<*>> = ArrayList()
  private var stateConsumer: ((View, State) -> Unit)? = null

  abstract fun initialState(): State

  @MainThread
  abstract fun bind()

  @MainThread
  abstract fun reduce(
    state: State,
    result: Result
  ): State

  override fun attachView(view: BaseView) {
    if (intentBinders.isEmpty()) {
      bind()
    }

    stateConsumer?.let { startStateSubscription(view as View) }

    for (intentBinder: Binder<*> in intentBinders) {
      bindIntents(view as View, intentBinder as Binder<Any>)
    }
  }

  override fun detachView() {
    shortLivedDisposable.clear()
  }

  override fun destroy() {
    stateDisposable.clear()
    reinitialize()
  }

  fun mergeResults(vararg intentsWithResult: Observable<out Result>): Observable<Result> {
    return Observable.merge(intentsWithResult.asIterable())
        .observeOn(AndroidSchedulers.mainThread())
  }

  fun <I> intent(consumer: (View) -> Observable<I>): Observable<I> {
    val binder = Binder<I>(intentRelay = UnicastSubject.create<I>(), consumer = consumer)
    intentBinders.add(binder)
    return binder.intentRelay
  }

  fun subscribeForStateUpdates(
    resultObservable: Observable<out Result>,
    consumer: (View, State) -> Unit
  ) {
    stateConsumer = consumer
    stateDisposable += resultObservable
        .scan(initialState()) { state, result -> reduce(state, result) }
        .doOnNext { Timber.d("State: ${it.toString()}") }
        .subscribeWith(DisposableIntentObserver(stateBehaviorSubject))
  }

  private fun startStateSubscription(view: View) {
    shortLivedDisposable += stateBehaviorSubject.subscribe { state -> stateConsumer?.invoke(view, state) }
  }

  private fun bindIntents(
    view: View,
    binder: Binder<Any>
  ) {
    val intentSubject = binder.intentRelay
    val intent = binder.consumer.invoke(view)
    shortLivedDisposable += intent.subscribeWith(DisposableIntentObserver(intentSubject))
  }

  private fun reinitialize() {
    intentBinders.clear()
  }

  private inner class Binder<I>(
    var intentRelay: Subject<I>,
    var consumer: (View) -> Observable<I>
  )
}