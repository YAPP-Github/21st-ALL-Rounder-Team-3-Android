package com.yapp.timitimi.presentation.ui.mypage.redux

import com.yapp.timitimi.domain.entity.UserProfile
import com.yapp.timitimi.redux.BaseIntent

sealed class MyPageIntent : BaseIntent {
    data class LoadScreen(
        val isLoading: Boolean = true,
        val userProfile: UserProfile = UserProfile.empty()
    ) : MyPageIntent()

    object ClickEditMyInfo : MyPageIntent()
}
