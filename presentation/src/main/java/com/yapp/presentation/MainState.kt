package com.yapp.presentation

import com.yapp.presentation.redux.BaseState

data class MainState(
    val text: String = "",
    val topButtonText: String = "",
    val bottomButtonText: String = "",
    val isClicked: Boolean = false
): BaseState
