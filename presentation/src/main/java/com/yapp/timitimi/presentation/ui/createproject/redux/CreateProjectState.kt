package com.yapp.timitimi.presentation.ui.createproject.redux

import com.yapp.timitimi.domain.entity.CreateProjectsInfo
import com.yapp.timitimi.presentation.ui.createproject.screen.CalenderDueDateType
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
    val openCalendarType: CalenderDueDateType = CalenderDueDateType.NONE
) : BaseState {
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

    fun toCreateProjectsInfoEntity() = CreateProjectsInfo(
        projectName,
        projectStartDate,
        projectEndDate,
        projectGoal,
        1,
        "BEFORE"
    )
}
