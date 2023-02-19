package com.yapp.timitimi.presentation.ui.edit.redux

import com.yapp.timitimi.domain.entity.Participant
import com.yapp.timitimi.presentation.ui.edit.ParticipantItem
import com.yapp.timitimi.redux.BaseIntent
import com.yapp.timitimi.redux.Reducer
import javax.inject.Inject

class EditProjectReducer @Inject constructor() : Reducer<EditProjectState> {
    override fun invoke(action: BaseIntent, state: EditProjectState): EditProjectState {
        var newState = state
        when (action) {
            is EditProjectIntent.ChangeProjectName -> {
                newState = newState.copy(
                    projectName = action.name
                )
            }

            is EditProjectIntent.ChangeProjectStartDate -> {
                newState = newState.copy(
                    projectStartDate = action.date,
                )
            }

            is EditProjectIntent.ChangeProjectEndDate -> {
                newState = newState.copy(
                    projectEndDate = action.date,
                )
            }

            is EditProjectIntent.ChangeProjectGoal -> {
                newState = newState.copy(
                    projectGoal = action.goal,
                )
            }

            is EditProjectIntent.ChangeProjectNameTextFieldFocused -> {
                newState = newState.copy(
                    hasProjectNameFieldFocused = action.hasFocused
                )
            }

            is EditProjectIntent.ClearProjectName -> {
                newState = newState.copy(
                    projectName = ""
                )
            }

            is EditProjectIntent.ClearProjectGoal -> {
                newState = newState.copy(
                    projectGoal = ""
                )
            }

            is EditProjectIntent.ChangeProjectGoalTextFieldFocused -> {
                newState = newState.copy(
                    hasProjectGoalFocused = action.hasFocused
                )
            }

            is EditProjectIntent.OpenDueDateCalendar
            -> {
                newState = newState.copy(
                    hasProjectGoalFocused = false,
                    hasProjectNameFieldFocused = false,
                    isCalendarVisible = true,
                    openCalendarType = action.dueDateType
                )
            }

            is EditProjectIntent.CloseCalendar -> {
                newState = newState.copy(
                    isCalendarVisible = false
                )
            }

            is EditProjectIntent.SelectStartProjectDate -> {
                newState = newState.copy(
                    projectStartDate = action.date,
                    isCalendarVisible = false
                )
            }

            is EditProjectIntent.SelectEndProjectDate -> {
                newState = newState.copy(
                    projectEndDate = action.date,
                    isCalendarVisible = false
                )
            }

            is EditProjectIntent.GetProject -> {
                action.project?.let {
                    newState = newState.copy(
                        projectName = it.name,
                        projectStartDate = it.startDate,
                        projectEndDate = it.dueDate,
                        projectGoal = it.goal,
                        participantList = it.participantInfos.map { participant ->
                            participant.toPresentationModel()
                        },
                        myId = it.myParticipantId,
                        projectId = it.id,
                    )
                }
            }
        }

        newState = newState.copy(
            isButtonEnabled = newState.isFilledAllField()
        )

        return newState
    }
}

private fun Participant.toPresentationModel() = ParticipantItem(
    id = id,
    isLeader = isLeader,
    profileUrl = imageUrl,
    nickName = name,
)