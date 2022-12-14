package com.yapp.presentation.ui.createproject.redux

import com.yapp.core.redux.BaseSingleEvent

sealed interface CreateProjectSingleEvent : BaseSingleEvent {
    object NavigateToMain : CreateProjectSingleEvent
    object NavigateOneStopPage: CreateProjectSingleEvent
    object NavigateToTwoStepPage: CreateProjectSingleEvent
    object Exit : CreateProjectSingleEvent
}