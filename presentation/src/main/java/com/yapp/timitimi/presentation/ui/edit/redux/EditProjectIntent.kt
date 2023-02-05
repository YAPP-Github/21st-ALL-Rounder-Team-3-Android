package com.yapp.timitimi.presentation.ui.edit.redux

import com.yapp.timitimi.presentation.ui.createproject.screen.CalenderDueDateType
import com.yapp.timitimi.redux.BaseIntent

sealed interface EditProjectIntent : BaseIntent {
    data class ChangeProjectName(val name: String): EditProjectIntent
    data class ChangeProjectStartDate(val date: String): EditProjectIntent
    data class ChangeProjectEndDate(val date: String): EditProjectIntent
    data class ChangeProjectGoal(val goal: String): EditProjectIntent
    data class ChangeProjectNameTextFieldFocused(val hasFocused: Boolean): EditProjectIntent
    data class ChangeProjectGoalTextFieldFocused(val hasFocused: Boolean): EditProjectIntent

    object ClearProjectName: EditProjectIntent
    object ClearProjectGoal: EditProjectIntent
    data class OpenDueDateCalendar(val dueDateType: CalenderDueDateType): EditProjectIntent
    object CloseCalendar: EditProjectIntent

    object ClickBackButton: EditProjectIntent
    object CompleteEdit : EditProjectIntent

    data class SelectStartProjectDate(
       val date: String
    ): EditProjectIntent

    data class SelectEndProjectDate(
        val date: String
    ): EditProjectIntent

}