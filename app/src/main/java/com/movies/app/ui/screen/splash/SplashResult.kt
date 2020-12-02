package com.movies.app.ui.screen.splash

sealed class SplashResult

class AnimateSplashResult : SplashResult()
class FinishSplashResult : SplashResult()