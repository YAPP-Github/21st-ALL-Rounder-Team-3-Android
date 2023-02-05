package com.yapp.timitimi.presentation.ui.login.redux

import com.yapp.timitimi.redux.BaseIntent
import com.yapp.timitimi.redux.BaseState
import com.yapp.timitimi.redux.Reducer
import javax.inject.Inject

class LoginReducer @Inject constructor() : Reducer<BaseState> {
    override fun invoke(action: BaseIntent, state: BaseState): BaseState {
        return state
    }
}
