package com.movies.app.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity

abstract class BaseActivity(@get:LayoutRes val layoutId: Int) : FragmentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layoutId)
    setupUI()
    setupListeners()
  }

  /**
   * Override in activity and process UI stuff that you need
   */
  open fun setupUI() {}

  /**
   * Override in activity and setup listeners
   */
  open fun setupListeners() {}
}
