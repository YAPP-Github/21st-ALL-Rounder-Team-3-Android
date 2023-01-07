package com.yapp.presentation.ui.login.redux

import com.yapp.core.redux.BaseIntent

sealed class LoginIntent : BaseIntent {
    data class KakaoLoginSucceed(val appToken: String): LoginIntent()
    object KakaoLoginFailed : LoginIntent()
}