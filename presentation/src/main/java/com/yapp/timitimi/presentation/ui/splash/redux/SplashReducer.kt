package com.yapp.timitimi.presentation.ui.splash.redux

import com.yapp.timitimi.redux.BaseIntent
import com.yapp.timitimi.redux.Reducer
import javax.inject.Inject

class SplashReducer @Inject constructor() : Reducer<SplashState> {
    override fun invoke(action: BaseIntent, state: SplashState): SplashState {
        var newState = state
        when (action) {
            is SplashIntent.GetAccessTokenSucceed -> {
                newState = newState.copy(
                    isLoading = false
                )
            }

            is SplashIntent.GetAccessTokenFailed -> {
                newState = newState.copy(
                    isLoading = false
                )
            }
        }

        return newState
    }
}
