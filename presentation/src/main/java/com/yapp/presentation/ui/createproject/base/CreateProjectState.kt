package com.yapp.presentation.ui.createproject.base

import com.yapp.core.redux.BaseState
import com.yapp.presentation.ui.createproject.onestep.ProjectDifficulty

data class CreateProjectState(
    val projectName: String = "",
    val hasProjectNameFieldFocused: Boolean = false,
    val projectStartDate: String? = null,
    val projectEndDate: String? = null,
    val projectGoal: String = "",
    val hasProjectGoalFocused: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isDropDownVisible: Boolean = false,
    val projectDifficulty: ProjectDifficulty = ProjectDifficulty.NONE,
    val isCalendarVisible: Boolean = false,
    val openCalendarType: CreateProjectIntent.DueDateType = CreateProjectIntent.DueDateType.NONE,
): BaseState {
    fun isAllFieldCompleted(): Boolean {
        return projectDifficulty != ProjectDifficulty.NONE
                && projectName.isNotBlank()
                && projectStartDate?.isNotEmpty() == true
                && projectEndDate?.isNotEmpty() == true
                && projectGoal.isNotBlank()
    }

    fun isNotInitializedStartDate(): Boolean {
        return projectStartDate.isNullOrEmpty()
    }

    fun isNotInitializedEndDate(): Boolean {
        return projectEndDate.isNullOrEmpty()
    }
}
