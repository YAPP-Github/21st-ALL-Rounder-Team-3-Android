package com.yapp.timitimi.presentation.ui.mypage.redux

import com.yapp.timitimi.redux.BaseSingleEvent

sealed class MyPageSingleEvent : BaseSingleEvent {
    object NavigateToProfile : MyPageSingleEvent()
}