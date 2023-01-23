package com.yapp.timitimi.presentation.ui.createproject.redux

import com.yapp.timitimi.redux.BaseSingleEvent

sealed interface CreateProjectSingleEvent : BaseSingleEvent {
    object NavigateToMain : CreateProjectSingleEvent
    object NavigateOneStopPage: CreateProjectSingleEvent
    object NavigateToTwoStepPage: CreateProjectSingleEvent

    data class ShowChooser(val id: String) : CreateProjectSingleEvent
    object Exit : CreateProjectSingleEvent
}