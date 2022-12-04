package com.yapp.presentation.ui.createproject

import com.yapp.core.redux.BaseState
import com.yapp.presentation.ui.createproject.onestep.ProjectDifficulty

data class CreateProjectState(
    val projectName: String = "",
    val hasProjectNameFieldFocused: Boolean = false,
    val projectStartDate: String = "시작날",
    val projectEndDate: String = "마지막날",
    val projectGoal: String = "",
    val hasProjectGoalFocused: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isDropDownVisible: Boolean = false,
    val projectDifficulty: ProjectDifficulty = ProjectDifficulty.NONE
): BaseState {
    fun isFilledAllField(): Boolean {
        return projectDifficulty != ProjectDifficulty.NONE
                && projectName.isNotBlank()
            //    && projectStartDate != "시작날"
             //   && projectEndDate != "마지막날"
                && projectGoal.isNotBlank()
    }
}
