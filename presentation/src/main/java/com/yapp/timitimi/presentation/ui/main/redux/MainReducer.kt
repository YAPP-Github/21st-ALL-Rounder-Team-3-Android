package com.yapp.timitimi.presentation.ui.main.redux

import com.yapp.timitimi.domain.entity.Participant
import com.yapp.timitimi.kotlin.immutableMap
import com.yapp.timitimi.redux.BaseIntent
import com.yapp.timitimi.redux.Reducer

class MainReducer : Reducer<MainState> {
    override fun invoke(action: BaseIntent, state: MainState): MainState {
        return when (action) {
            is MainIntent.Init -> {
                state.copy(
                    project = action.project?.toPresentationModel()?.copy(
                        memberCount = state.members.size
                    ) ?: ProjectItem(),
                    members = action.participants.immutableMap(Participant::toPresentationModel),
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
