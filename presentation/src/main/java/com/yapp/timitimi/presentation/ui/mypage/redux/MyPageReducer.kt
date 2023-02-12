package com.yapp.timitimi.presentation.ui.mypage.redux

import com.yapp.timitimi.redux.BaseIntent
import com.yapp.timitimi.redux.Reducer
import javax.inject.Inject

class MyPageReducer @Inject constructor() : Reducer<MyPageState> {
    override fun invoke(action: BaseIntent, state: MyPageState): MyPageState {
        var newState = state
        when (action) {
            is MyPageIntent.LoadScreen -> {
                newState = newState.copy(
                    isLoading = action.isLoading,
                    userProfile = action.userProfile
                )
            }
        }

        return newState
    }
}
