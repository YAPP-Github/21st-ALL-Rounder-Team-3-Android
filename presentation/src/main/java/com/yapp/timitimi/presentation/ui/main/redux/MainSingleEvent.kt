package com.yapp.timitimi.presentation.ui.main.redux

import com.yapp.timitimi.redux.BaseSingleEvent

sealed interface MainSingleEvent : BaseSingleEvent {
    object NavigateToProjectList : MainSingleEvent
    object NavigateToEditProject : MainSingleEvent
    object NavigateToNotificationList : MainSingleEvent
    object NavigateToCreateTask : MainSingleEvent
    object NavigateToTaskDetail : MainSingleEvent
    object NavigateToInviteMember : MainSingleEvent
}