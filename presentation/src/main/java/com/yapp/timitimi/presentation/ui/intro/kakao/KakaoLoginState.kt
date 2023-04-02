package com.yapp.timitimi.presentation.ui.intro.kakao

import androidx.annotation.Keep
import com.kakao.sdk.auth.model.OAuthToken

@Keep
sealed class KakaoLoginState {
    data class Succeed(
        val token: OAuthToken
    ): KakaoLoginState()

    data class Error(
        val msg: String
    ): KakaoLoginState()
}