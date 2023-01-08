package com.yapp.timitimi.presentation.ui.main.redux

import com.yapp.timitimi.redux.BaseIntent
import com.yapp.timitimi.redux.Reducer

class MainReducer : Reducer<MainState> {
    override fun invoke(action: BaseIntent, state: MainState): MainState {
        return when (action) {
            is MainIntent.OnSelectProfile -> {
                state.copy(
                    selectedProfileIndex = action.index
                )
            }

            is MainIntent.OnClickDropBox -> {
                state.copy(
                    isTaskDropDownExpanded = action.isExpanded.not()
                )
            }

            else -> {
                state.copy()
            }
        }
    }
}
