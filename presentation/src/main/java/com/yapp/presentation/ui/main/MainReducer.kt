package com.yapp.presentation.ui.main

import com.yapp.core.redux.BaseIntent
import timber.log.Timber

class MainReducer : com.yapp.core.redux.Reducer<MainState> {
    override fun invoke(action: BaseIntent, state: MainState): MainState {
        var newState = state
        when (action) {
            is MainIntent.CompleteLoading -> {
                newState = newState.copy(
                    text = "complete init",
                )
            }
            is MainIntent.ChangeTopButtonText -> {
                newState = newState.copy(
                    topButtonText = "TOP!",
                    isClicked = false
                )
            }
            is MainIntent.ChangeBottomButtonText -> {
                newState = newState.copy(
                    bottomButtonText = "BOTTOM!",
                    isClicked = true
                )
            }
        }

        Timber.e(newState.toString())
        return newState
    }
}