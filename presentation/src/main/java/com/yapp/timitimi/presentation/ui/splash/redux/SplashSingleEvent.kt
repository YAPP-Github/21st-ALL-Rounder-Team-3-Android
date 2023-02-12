package com.yapp.timitimi.presentation.ui.splash.redux

import com.yapp.timitimi.redux.BaseSingleEvent

sealed class SplashSingleEvent : BaseSingleEvent {
    object NavigateToMain : SplashSingleEvent()
    object NavigateToCreateProject : SplashSingleEvent()
    object NavigateToLogin : SplashSingleEvent()
    object RenewAccessToken : SplashSingleEvent()
}