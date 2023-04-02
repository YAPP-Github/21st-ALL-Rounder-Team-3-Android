package com.yapp.timitimi.presentation.ui.intro.redux

import com.yapp.timitimi.redux.BaseIntent

sealed class IntroIntent : BaseIntent {
    data class KakaoLoginSucceed(
        val appToken: String
        ): IntroIntent()
    object KakaoLoginFailed : IntroIntent()
    object Login: IntroIntent()
}
