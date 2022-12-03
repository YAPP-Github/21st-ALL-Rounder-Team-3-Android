package com.yapp.presentation.ui.createproject

import com.yapp.core.redux.BaseState

data class CreateProjectState(
    val projectName: String = "",
    val projectStartDate: String = "시작날",
    val projectEndDate: String = "마지막날",
    val projectGoal: String = "",
    val isButtonEnabled: Boolean = false,
    val isDropDownVisible: Boolean = false,
): BaseState
