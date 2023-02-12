package com.yapp.timitimi.presentation.ui.splash.redux

import com.yapp.timitimi.redux.BaseIntent

sealed class SplashIntent : BaseIntent {
    object GetAccessTokenSucceed : SplashIntent()
    object GetAccessTokenFailed : SplashIntent()
    object NeedRefreshToken : SplashIntent()

    data class GetAccessTokenSucceedAndInvitedUser(val invitedId: String): SplashIntent()

}