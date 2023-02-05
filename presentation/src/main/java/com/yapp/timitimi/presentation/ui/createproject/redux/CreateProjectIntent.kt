package com.yapp.timitimi.presentation.ui.createproject.redux

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
    data class OpenDueDateCalendar(val dueDateType: DueDateType): CreateProjectIntent
    object CloseCalendar: CreateProjectIntent

    object ShareProjectDeeplink: CreateProjectIntent

    data class ClickBackButton(
        val progress: Float
    ): CreateProjectIntent

    object ClickNextButton: CreateProjectIntent

    object StartMain : CreateProjectIntent

    data class SelectStartProjectDate(
        val day: Int,
        val month: Int,
        val year: Int
    ): CreateProjectIntent

    data class SelectEndProjectDate(
        val day: Int,
        val month: Int,
        val year: Int
    ): CreateProjectIntent


    enum class DueDateType {
        NONE, START, END
    }

}