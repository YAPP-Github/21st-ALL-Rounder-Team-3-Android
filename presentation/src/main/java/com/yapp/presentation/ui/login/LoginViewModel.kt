package com.yapp.presentation.ui.login

import com.yapp.core.base.BaseViewModel
import com.yapp.core.redux.BaseMiddleware
import com.yapp.core.redux.BaseState
import com.yapp.core.redux.EmptyViewState
import com.yapp.presentation.ui.login.redux.LoginIntent
import com.yapp.presentation.ui.login.redux.LoginMiddleware
import com.yapp.presentation.ui.login.redux.LoginSingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val middleware: LoginMiddleware
): BaseViewModel<
        LoginIntent,
        BaseState,
        LoginSingleEvent>() {

    override fun registerMiddleware(): List<BaseMiddleware<LoginIntent, LoginSingleEvent>> {
        return listOf(middleware)
    }
    override fun processError(throwable: Throwable) {
        Timber.e(throwable.localizedMessage)
    }

    override fun getInitialState(): BaseState = EmptyViewState
    init {
        start()
    }
}