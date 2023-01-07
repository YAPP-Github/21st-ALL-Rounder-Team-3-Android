package com.yapp.presentation.ui.main.redux

import com.yapp.core.redux.BaseIntent
import com.yapp.core.redux.Reducer

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
