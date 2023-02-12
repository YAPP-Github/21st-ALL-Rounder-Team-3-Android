package com.yapp.timitimi.presentation.ui.main.redux

import com.yapp.timitimi.domain.entity.Participant
import com.yapp.timitimi.redux.BaseIntent
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

sealed interface MainIntent : BaseIntent {
    data class CheckNewUser(
        val isFirstUser: Boolean = true,
    ) : MainIntent

    object ClickGuideScreen : MainIntent
    data class Init(
        val projectId: Int,
        val participants: ImmutableList<Participant> = persistentListOf(),
    ) : MainIntent

    object ClickBackButton : MainIntent
    object ClickEditButton : MainIntent
    object ClickNotificationButton : MainIntent
    object SelectAddProfile : MainIntent
    data class ClickFab(
        val projectId: Int,
    ) : MainIntent

    data class SelectProfile(val index: Int) : MainIntent
    data class ClickTaskItem(
        val projectId: Int,
        val taskId: Int,
        val isMe: Boolean,
    ) : MainIntent
}