package com.yapp.timitimi.presentation.ui.main.redux

import com.yapp.timitimi.redux.BaseSingleEvent

sealed interface MainSingleEvent : BaseSingleEvent {
    object NavigateToProjectList : MainSingleEvent
    object NavigateToEditProject : MainSingleEvent
    object NavigateToNotificationList : MainSingleEvent
    data class NavigateToCreateTask(
        val projectId: Int,
    ) : MainSingleEvent

    data class NavigateToTaskDetail(
        val projectId: Int,
        val taskId: Int,
        val isMe: Boolean,
    ) : MainSingleEvent

    object NavigateToInviteMember : MainSingleEvent
}