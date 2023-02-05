package com.yapp.timitimi.presentation.ui.invite.redux

import com.yapp.timitimi.redux.BaseSingleEvent

sealed interface InviteProjectSingleEvent : BaseSingleEvent {
    object NavigateToMain : InviteProjectSingleEvent
    object Exit : InviteProjectSingleEvent
}