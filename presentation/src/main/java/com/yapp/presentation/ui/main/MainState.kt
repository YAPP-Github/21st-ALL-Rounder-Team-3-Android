package com.yapp.presentation.ui.main

import com.yapp.core.redux.BaseState

data class MainState(
    val text: String = "",
    val topButtonText: String = "",
    val bottomButtonText: String = "",
    val isClicked: Boolean = false
): BaseState
