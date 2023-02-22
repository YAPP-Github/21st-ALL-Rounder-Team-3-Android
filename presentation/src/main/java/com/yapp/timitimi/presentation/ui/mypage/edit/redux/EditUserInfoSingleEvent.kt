package com.yapp.timitimi.presentation.ui.mypage.edit.redux

import com.yapp.timitimi.redux.BaseSingleEvent

sealed class EditUserInfoSingleEvent : BaseSingleEvent {
    object NavigateToBackScreen : EditUserInfoSingleEvent()

    object ShowChangeProfileBottomSheet : EditUserInfoSingleEvent()

    object DismissBottomSheet : EditUserInfoSingleEvent()

    object ShowChangeTimiTimiImageBottomSheet: EditUserInfoSingleEvent()
}