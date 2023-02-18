package com.yapp.timitimi.presentation.ui.edit.redux

import com.yapp.timitimi.presentation.ui.createproject.screen.CalenderDueDateType
import com.yapp.timitimi.presentation.ui.edit.ParticipantItem
import com.yapp.timitimi.redux.BaseState

data class EditProjectState(
    val myId: Int = 0,
    val projectName: String = "",
    val hasProjectNameFieldFocused: Boolean = false,
    val projectStartDate: String = "시작날",
    val projectEndDate: String = "마지막날",
    val projectGoal: String = "",
    val hasProjectGoalFocused: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isDropDownVisible: Boolean = false,
    val isCalendarVisible: Boolean = false,
    val openCalendarType: CalenderDueDateType = CalenderDueDateType.NONE,
    val participantList: List<ParticipantItem> = emptyList()
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
}
