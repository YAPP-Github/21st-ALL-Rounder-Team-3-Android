package com.yapp.presentation

import com.yapp.presentation.redux.BaseIntent
import com.yapp.presentation.redux.Reducer
import timber.log.Timber

class MainReducer : Reducer<MainState> {
    override fun invoke(action: BaseIntent, state: MainState): MainState {
        Timber.e("invoke: $action")
        var newState = state
        when (action) {
            is MainIntent.CompleteLoading -> {
                newState = newState.copy(
                    text = "complete init"
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

        return newState
    }
}
