package com.yapp.timitimi.presentation.ui.mypage.edit.redux

import com.yapp.timitimi.domain.entity.UserProfile
import com.yapp.timitimi.redux.BaseState

data class EditUserInfoState(
    val isLoading: Boolean = true,
    val hasNicknameFieldFocused: Boolean = true,
    val hasDescriptionFieldFocused: Boolean = false,
    val userProfile: UserProfile = UserProfile.empty()
): BaseState