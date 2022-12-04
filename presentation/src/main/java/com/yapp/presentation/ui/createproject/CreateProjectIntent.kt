package com.yapp.presentation.ui.createproject

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
}