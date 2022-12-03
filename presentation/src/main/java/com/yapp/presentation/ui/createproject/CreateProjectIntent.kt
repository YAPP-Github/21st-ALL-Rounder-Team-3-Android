package com.yapp.presentation.ui.createproject

import com.yapp.core.redux.BaseIntent

sealed interface CreateProjectIntent : BaseIntent {
    data class ChangeProjectName(val name: String): CreateProjectIntent
    data class ChangeProjectStartDate(val date: String): CreateProjectIntent
    data class ChangeProjectEndDate(val date: String): CreateProjectIntent
    data class ChangeProjectGoal(val goal: String): CreateProjectIntent
    object OnClickDropDown: CreateProjectIntent
}