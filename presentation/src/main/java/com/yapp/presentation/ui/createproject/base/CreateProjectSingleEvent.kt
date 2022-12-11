package com.yapp.presentation.ui.createproject.base

import com.yapp.core.redux.BaseSingleEvent

sealed class CreateProjectSingleEvent : BaseSingleEvent {
    object NavigateToMain : CreateProjectSingleEvent()
    object NavigateOneStopPage: CreateProjectSingleEvent()
    object NavigateToTwoStepPage: CreateProjectSingleEvent()
    object Exit : CreateProjectSingleEvent()

    data class ShowToast(val msg: String): CreateProjectSingleEvent()
}