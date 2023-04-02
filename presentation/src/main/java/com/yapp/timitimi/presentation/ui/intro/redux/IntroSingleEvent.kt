package com.yapp.timitimi.presentation.ui.intro.redux

import com.yapp.timitimi.redux.BaseSingleEvent

sealed class IntroSingleEvent : BaseSingleEvent {
    object NavigateToCreateProject: IntroSingleEvent()
    object TryKakaoLogin: IntroSingleEvent()
    object LoginFailed: IntroSingleEvent()
}
