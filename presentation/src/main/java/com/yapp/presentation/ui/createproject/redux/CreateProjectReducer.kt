package com.yapp.presentation.ui.createproject.redux

import com.yapp.core.redux.BaseIntent
import com.yapp.core.redux.Reducer
import javax.inject.Inject

class CreateProjectReducer @Inject constructor() : Reducer<CreateProjectState> {
    override fun invoke(action: BaseIntent, state: CreateProjectState): CreateProjectState {
        var newState = state
        when (action) {
            is CreateProjectIntent.ChangeProjectName -> {
                newState = newState.copy(
                    projectName = action.name
                )
            }

            is CreateProjectIntent.ChangeProjectStartDate -> {
                newState = newState.copy(
                    projectStartDate = action.date,
                )
            }

            is CreateProjectIntent.ChangeProjectEndDate -> {
                newState = newState.copy(
                    projectEndDate = action.date,
                )
            }

            is CreateProjectIntent.ChangeProjectGoal -> {
                newState = newState.copy(
                    projectGoal = action.goal,
                )
            }

            is CreateProjectIntent.OnClickDropDown -> {
                newState = newState.copy(
                    isDropDownVisible = newState.isDropDownVisible.not(),
                )
            }

            is CreateProjectIntent.OnClickDropDownItem -> {
                newState = newState.copy(
                    projectDifficulty = action.difficulty,
                    isDropDownVisible = newState.isDropDownVisible.not(),
                )
            }

            is CreateProjectIntent.ChangeProjectNameTextFieldFocused -> {
                newState = newState.copy(
                    hasProjectNameFieldFocused = action.hasFocused
                )
            }

            is CreateProjectIntent.ClearProjectName -> {
                newState = newState.copy(
                    projectName = ""
                )
            }

            is CreateProjectIntent.ClearProjectGoal -> {
                newState = newState.copy(
                    projectGoal = ""
                )
            }

            is CreateProjectIntent.ChangeProjectGoalTextFieldFocused -> {
                newState = newState.copy(
                    hasProjectGoalFocused = action.hasFocused
                )
            }

            is CreateProjectIntent.OpenDueDateCalendar
            -> {
                newState = newState.copy(
                    hasProjectGoalFocused = false,
                    hasProjectNameFieldFocused = false,
                    isCalendarVisible = true,
                    openCalendarType = action.dueDateType
                )
            }

            is CreateProjectIntent.CloseCalendar -> {
                newState = newState.copy(
                    isCalendarVisible = false
                )
            }

            is CreateProjectIntent.SelectStartProjectDate -> {
                newState = newState.copy(
                    projectStartDate = "${action.year}.${action.month}.${action.day}",
                    isCalendarVisible = false
                )
            }

            is CreateProjectIntent.SelectEndProjectDate -> {
                newState = newState.copy(
                    projectEndDate = "${action.year}.${action.month}.${action.day}",
                    isCalendarVisible = false
                )
            }
        }

        newState = newState.copy(
            isButtonEnabled = newState.isFilledAllField()
        )

        return newState
    }
}