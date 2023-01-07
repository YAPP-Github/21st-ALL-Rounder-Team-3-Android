package com.yapp.presentation.ui.login.redux

import com.yapp.core.redux.BaseSingleEvent

sealed class LoginSingleEvent : BaseSingleEvent {
    object NavigateToCreateProject: LoginSingleEvent()
    object ShowToast : LoginSingleEvent()
}
