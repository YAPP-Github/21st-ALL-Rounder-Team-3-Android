package com.yapp.timitimi.presentation.ui.splash.redux

import com.yapp.timitimi.redux.BaseSingleEvent

sealed interface SplashSingleEvent : BaseSingleEvent {
    object NavigateToMain : SplashSingleEvent
    object NavigateToLogin : SplashSingleEvent
    object RenewAccessToken : SplashSingleEvent
}