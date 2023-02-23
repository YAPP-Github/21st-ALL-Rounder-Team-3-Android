@file:RequiresApi(Build.VERSION_CODES.O)

package com.yapp.timitimi.presentation.ui.main.redux

import android.os.Build
import androidx.annotation.RequiresApi
import com.yapp.timitimi.component.TaskType
import com.yapp.timitimi.domain.entity.Participant
import com.yapp.timitimi.domain.entity.Representative
import com.yapp.timitimi.domain.entity.Task
import com.yapp.timitimi.domain.entity.TaskStatus
import com.yapp.timitimi.kotlin.immutableMap
import com.yapp.timitimi.redux.BaseIntent
import com.yapp.timitimi.redux.Reducer
import com.yapp.timitimi.util.getDdayFromToday
import com.yapp.timitimi.util.toDotDate
import com.yapp.timitimi.util.toStringDday

class MainReducer : Reducer<MainState> {
    override fun invoke(action: BaseIntent, state: MainState): MainState {
        return when (action) {
            is MainIntent.Init -> {
                state.copy(
                    project = action.project.toPresentationModel().copy(
                        memberCount = state.members.size
                    ),
                    members = action.participants.immutableMap(Participant::toPresentationModel),
                    tasks = action.tasks.immutableMap(Task::toPresentationModel)
                )
            }

            is MainIntent.SelectProfile -> {
                state.copy(
                    selectedProfileIndex = action.index
                )
            }

            MainIntent.ClickGuideScreen -> {
                state.copy(
                    currentStep = state.currentStep.plus(1)
                )
            }

            else -> {
                state.copy()
            }
        }
    }
}

private fun Participant.toPresentationModel() = Member(
    id = id,
    isLeader = isLeader,
    profile = imageUrl,
    name = name,
)


private fun Task.toPresentationModel() = TaskItem(
    taskType = when (taskStatus) {
        TaskStatus.BEFORE -> TaskType.Before
        TaskStatus.INPROGRESS -> TaskType.InProgress
        TaskStatus.LATE -> TaskType.Late
        TaskStatus.FEEDBACK -> TaskType.FeedBack(
            currentConfirmation = confirmCount,
            totalConfirmation = feedbackRequiredPersonnel,
        )

        TaskStatus.DONE -> TaskType.Done
    },
    id = id,
    member = representative.toPresentationModel(),
    startDate = startDate.toDotDate(),
    endDate = dueDate.toDotDate(),
    dDay = feedbackDueDate.getDdayFromToday().toStringDday(),
    title = title,
    memo = memo,
)

private fun Representative.toPresentationModel() = Member(
    id = participantId,
    name = name,
    profile = imageUrl,
)