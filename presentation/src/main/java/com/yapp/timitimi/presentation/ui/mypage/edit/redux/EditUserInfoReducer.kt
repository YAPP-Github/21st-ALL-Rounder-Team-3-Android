package com.yapp.timitimi.presentation.ui.mypage.edit.redux

import com.yapp.timitimi.redux.BaseIntent
import com.yapp.timitimi.redux.Reducer
import javax.inject.Inject

class EditUserInfoReducer @Inject constructor() : Reducer<EditUserInfoState> {
    override fun invoke(action: BaseIntent, state: EditUserInfoState): EditUserInfoState {
        var newState = state
        when (action) {
            is EditUserInfoIntent.LoadScreen -> {
                newState = newState.copy(
                    isLoading = action.isLoading,
                    userProfile = action.userProfile
                )
            }
        }

        return newState
    }
}
