package com.movies.app.ui.splash

sealed class SplashResult

class AnimateSplashResult : SplashResult()
class FinishSplashResult : SplashResult()