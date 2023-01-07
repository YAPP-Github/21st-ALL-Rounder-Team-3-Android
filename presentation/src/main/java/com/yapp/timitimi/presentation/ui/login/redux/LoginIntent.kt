package com.yapp.timitimi.presentation.ui.login.redux

import com.yapp.timitimi.redux.BaseIntent

sealed class LoginIntent : BaseIntent {
    data class KakaoLoginSucceed(val appToken: String): LoginIntent()
    object KakaoLoginFailed : LoginIntent()
}