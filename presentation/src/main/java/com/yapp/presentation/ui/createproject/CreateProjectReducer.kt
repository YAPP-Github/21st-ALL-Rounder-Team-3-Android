package com.yapp.presentation.ui.createproject

import com.yapp.core.redux.BaseIntent
import com.yapp.core.redux.Reducer
import javax.inject.Inject

class CreateProjectReducer @Inject constructor() : Reducer<CreateProjectState> {
    override fun invoke(action: BaseIntent, state: CreateProjectState): CreateProjectState {
        var newState = state
        when (action) {
            is CreateProjectIntent.ChangeProjectName -> {
                newState = newState.copy(
                    projectName = action.name,
                    isButtonEnabled = action.name.isNotEmpty()
                )
            }
            is CreateProjectIntent.ChangeProjectStartDate -> {
                newState = newState.copy(
                    projectName = action.date,
                )
            }
            is CreateProjectIntent.ChangeProjectEndDate -> {
                newState = newState.copy(
                    projectName = action.date,
                )
            }
            is CreateProjectIntent.ChangeProjectGoal -> {
                newState = newState.copy(
                    projectName = action.goal,
                )
            }

            is CreateProjectIntent.OnClickDropDown -> {
                newState = newState.copy(
                    isDropDownVisible = newState.isDropDownVisible.not()
                )
            }
        }
        return newState
    }
}
