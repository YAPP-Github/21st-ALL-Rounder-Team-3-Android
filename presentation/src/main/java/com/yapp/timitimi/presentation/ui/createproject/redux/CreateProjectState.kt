package com.yapp.timitimi.presentation.ui.createproject.redux

import com.yapp.timitimi.redux.BaseState

data class CreateProjectState(
    val projectName: String = "",
    val hasProjectNameFieldFocused: Boolean = false,
    val projectStartDate: String = "시작날",
    val projectEndDate: String = "마지막날",
    val projectGoal: String = "",
    val hasProjectGoalFocused: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isDropDownVisible: Boolean = false,
    val isCalendarVisible: Boolean = false,
    val openCalendarType: CreateProjectIntent.DueDateType = CreateProjectIntent.DueDateType.NONE
): BaseState {
    fun isFilledAllField(): Boolean {
        return projectName.isNotBlank()
                && projectStartDate != "시작날"
                && projectEndDate != "마지막날"
                && projectGoal.isNotBlank()
    }

    fun isNotInitializedStartDate(): Boolean {
        return projectStartDate == "시작날"
    }

    fun isNotInitializedEndDate(): Boolean {
        return projectEndDate == "마지막날"
    }
}
