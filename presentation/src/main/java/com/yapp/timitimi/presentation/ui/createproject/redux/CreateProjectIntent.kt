package com.yapp.timitimi.presentation.ui.createproject.redux

import com.yapp.timitimi.presentation.ui.createproject.screen.CalenderDueDateType
import com.yapp.timitimi.redux.BaseIntent

sealed interface CreateProjectIntent : BaseIntent {
    data class ChangeProjectName(val name: String): CreateProjectIntent
    data class ChangeProjectStartDate(val date: String): CreateProjectIntent
    data class ChangeProjectEndDate(val date: String): CreateProjectIntent
    data class ChangeProjectGoal(val goal: String): CreateProjectIntent
    data class ChangeProjectNameTextFieldFocused(val hasFocused: Boolean): CreateProjectIntent
    data class ChangeProjectGoalTextFieldFocused(val hasFocused: Boolean): CreateProjectIntent

    object ClearProjectName: CreateProjectIntent
    object ClearProjectGoal: CreateProjectIntent
    data class OpenDueDateCalendar(val dueDateType: CalenderDueDateType): CreateProjectIntent
    object CloseCalendar: CreateProjectIntent

    data class ShareProjectDeeplink(val projectId: String): CreateProjectIntent

    data class ClickBackButton(
        val progress: Float
    ): CreateProjectIntent

    data class ClickNextButton(val state: CreateProjectState): CreateProjectIntent

    object StartMain : CreateProjectIntent

    data class SelectStartProjectDate(
        val date: String
    ): CreateProjectIntent

    data class SelectEndProjectDate(
        val date: String
    ): CreateProjectIntent
}