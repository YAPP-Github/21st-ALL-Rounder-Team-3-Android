package com.yapp.timitimi.presentation.ui.login.redux

import com.yapp.timitimi.redux.BaseSingleEvent

sealed class LoginSingleEvent : BaseSingleEvent {
    object NavigateToCreateProject: LoginSingleEvent()
    object Finish: LoginSingleEvent()
    data class ShowToast(val msg: String) : LoginSingleEvent()
}
