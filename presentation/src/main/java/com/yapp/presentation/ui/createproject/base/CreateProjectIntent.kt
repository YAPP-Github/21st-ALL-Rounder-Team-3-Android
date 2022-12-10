package com.yapp.presentation.ui.createproject.base

import com.yapp.core.redux.BaseIntent
import com.yapp.presentation.ui.createproject.onestep.ProjectDifficulty

sealed interface CreateProjectIntent : BaseIntent {
    data class ChangeProjectName(val name: String): CreateProjectIntent
    data class ChangeProjectStartDate(val date: String): CreateProjectIntent
    data class ChangeProjectEndDate(val date: String): CreateProjectIntent
    data class ChangeProjectGoal(val goal: String): CreateProjectIntent
    object OnClickDropDown: CreateProjectIntent
    data class OnClickDropDownItem(val difficulty: ProjectDifficulty): CreateProjectIntent

    data class ChangeProjectNameTextFieldFocused(val hasFocused: Boolean): CreateProjectIntent
    data class ChangeProjectGoalTextFieldFocused(val hasFocused: Boolean): CreateProjectIntent

    object ClearProjectName: CreateProjectIntent
    object ClearProjectGoal: CreateProjectIntent
    data class OpenDueDateCalendar(val dueDateType: DueDateType): CreateProjectIntent
    object CloseCalendar: CreateProjectIntent

    object ClickBackButton: CreateProjectIntent

    object ClickNextButton: CreateProjectIntent

    data class ClickAppBarBackButton(
        val progress: Float
    ): CreateProjectIntent

    
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