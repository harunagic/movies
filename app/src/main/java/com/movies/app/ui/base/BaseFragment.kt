package com.movies.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.movies.app.App
import com.movies.app.di.component.AppComponent
import com.movies.app.ui.base.mvi.Presenter

abstract class BaseFragment(@get:LayoutRes val layoutId: Int) : Fragment() {

  abstract val presenter: Presenter?

  abstract fun inject(appComponent: AppComponent)

  override fun onCreate(savedInstanceState: Bundle?) {
    inject(App.appComponent)
    super.onCreate(savedInstanceState)
    retainInstance = true
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(layoutId, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setupUI()
    setupListeners()
  }

  final override fun onStart() {
    super.onStart()
    presenter?.attachView(this as BaseView)
  }

  final override fun onStop() {
    super.onStop()
    presenter?.detachView()
  }

  final override fun onDestroy() {
    super.onDestroy()
    if (activity?.isChangingConfigurations == false) {
      presenter?.destroy()
    }
  }

  /**
   * Override in fragment and process UI stuff that you need
   */
  open fun setupUI() {

  }

  /**
   * Override in fragment and setup listeners
   */
  open fun setupListeners() {

  }
}