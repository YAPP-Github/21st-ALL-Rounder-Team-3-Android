package com.yapp.timitimi.presentation.ui.edit.redux

import com.yapp.timitimi.redux.BaseSingleEvent

sealed interface EditProjectSingleEvent : BaseSingleEvent {
    object NavigateToMain : EditProjectSingleEvent
    object Exit : EditProjectSingleEvent
}