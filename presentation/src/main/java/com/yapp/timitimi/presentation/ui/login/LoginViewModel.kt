package com.yapp.timitimi.presentation.ui.login

import com.yapp.timitimi.base.BaseViewModel
import com.yapp.timitimi.presentation.ui.login.redux.LoginIntent
import com.yapp.timitimi.presentation.ui.login.redux.LoginMiddleware
import com.yapp.timitimi.presentation.ui.login.redux.LoginReducer
import com.yapp.timitimi.presentation.ui.login.redux.LoginSingleEvent
import com.yapp.timitimi.redux.BaseMiddleware
import com.yapp.timitimi.redux.BaseState
import com.yapp.timitimi.redux.EmptyViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val middleware: LoginMiddleware,
    private val reducer: LoginReducer
): BaseViewModel<
        LoginIntent,
        BaseState,
        LoginSingleEvent>() {

    override fun registerMiddleware(): List<BaseMiddleware<LoginIntent, LoginSingleEvent>> {
        return listOf(middleware)
    }

    override fun registerReducer() = reducer

    override fun processError(throwable: Throwable) {
        Timber.e(throwable.localizedMessage)
    }

    override fun getInitialState(): BaseState = EmptyViewState
    init {
        start()
    }
}