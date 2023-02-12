package com.yapp.timitimi.presentation.ui.mypage.redux

import com.yapp.timitimi.domain.entity.UserProfile
import com.yapp.timitimi.redux.BaseState

data class MyPageState(
    val isLoading: Boolean = true,
    val userProfile: UserProfile = UserProfile.empty()
): BaseState