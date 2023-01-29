package com.yapp.timitimi.presentation.ui.main.redux

import com.yapp.timitimi.domain.entity.Participant
import com.yapp.timitimi.redux.BaseIntent
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

sealed interface MainIntent : BaseIntent {
    object OnClickBackButton : MainIntent
    object OnClickEditButton : MainIntent
    object OnClickNotificationButton : MainIntent
    object OnSelectAddProfile : MainIntent
    object OnClickFab : MainIntent
    data class Init(
        val projectId: String,
        val participants: ImmutableList<Participant> = persistentListOf(),
    ) : MainIntent
    data class OnClickDropBox(val isExpanded: Boolean) : MainIntent
    data class OnSelectProfile(val index: Int) : MainIntent
    data class OnClickTaskItem(val index: Int) : MainIntent
}