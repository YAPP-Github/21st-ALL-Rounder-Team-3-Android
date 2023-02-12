package com.yapp.timitimi.presentation.ui.mypage.edit.redux

import com.yapp.timitimi.domain.entity.UserProfile
import com.yapp.timitimi.redux.BaseIntent

sealed class EditUserInfoIntent : BaseIntent {
    data class LoadScreen(
        val isLoading: Boolean = true,
        val userProfile: UserProfile = UserProfile.empty()
    ) : EditUserInfoIntent()

    object ClickEditMyInfo : EditUserInfoIntent()

    object ClearNicknameField: EditUserInfoIntent()

    data class ChangeNicknameTextFieldFocused(
        val hasFocused: Boolean
    ): EditUserInfoIntent()

    data class ChangeDescriptionTextFieldFocused(
        val hasFocused: Boolean
    ): EditUserInfoIntent()

    object SaveUserInfo: EditUserInfoIntent()
}
