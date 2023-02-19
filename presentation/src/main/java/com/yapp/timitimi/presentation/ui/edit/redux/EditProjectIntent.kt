package com.yapp.timitimi.presentation.ui.edit.redux

import com.yapp.timitimi.domain.entity.CreateProjectsInfo
import com.yapp.timitimi.domain.entity.EditProjectInfo
import com.yapp.timitimi.domain.entity.Project
import com.yapp.timitimi.presentation.ui.createproject.screen.CalenderDueDateType
import com.yapp.timitimi.redux.BaseIntent

sealed interface EditProjectIntent : BaseIntent {
    data class GetProject(
        val projectId: Int,
        val project: Project? = null,
    ) : EditProjectIntent

    data class ChangeProjectName(val name: String) : EditProjectIntent
    data class ChangeProjectStartDate(val date: String) : EditProjectIntent
    data class ChangeProjectEndDate(val date: String) : EditProjectIntent
    data class ChangeProjectGoal(val goal: String) : EditProjectIntent
    data class ChangeProjectNameTextFieldFocused(val hasFocused: Boolean) : EditProjectIntent
    data class ChangeProjectGoalTextFieldFocused(val hasFocused: Boolean) : EditProjectIntent

    object ClearProjectName : EditProjectIntent
    object ClearProjectGoal : EditProjectIntent
    data class OpenDueDateCalendar(val dueDateType: CalenderDueDateType) : EditProjectIntent
    object CloseCalendar : EditProjectIntent

    object ClickBackButton : EditProjectIntent
    data class CompleteEdit(
        val projectId: Int,
        val projectInfo: EditProjectInfo,
    ) : EditProjectIntent

    data class SelectStartProjectDate(
        val date: String
    ) : EditProjectIntent

    data class SelectEndProjectDate(
        val date: String
    ) : EditProjectIntent

}