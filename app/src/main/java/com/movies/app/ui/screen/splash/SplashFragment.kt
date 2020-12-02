package com.movies.app.ui.screen.splash

import androidx.lifecycle.Lifecycle.Event
import androidx.navigation.fragment.findNavController
import com.movies.app.R
import com.movies.app.di.component.AppComponent
import com.movies.app.ui.base.BaseFragment
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Observable
import kotlinx.android.synthetic.main.splash_fragment.logo
import javax.inject.Inject

class SplashFragment : BaseFragment(R.layout.splash_fragment), SplashView {

  @Inject override lateinit var presenter: SplashPresenter

  override fun inject(appComponent: AppComponent) {
    appComponent.inject(this)
  }

  override fun onCreated(): Observable<Event> = RxLifecycle.onStart(this)

  override fun update(uiState: SplashUiState) {
    // Animate logo state
    if (uiState.animateLogo) {
      logo.animate()
          .alpha(1.0f)
          .setDuration(800)
          .start()
    }

    // Finish splash screen state
    if (uiState.finishSplash) {
      findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMovieFragment())
    }
  }
}
