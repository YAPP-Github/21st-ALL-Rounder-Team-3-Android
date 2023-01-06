package com.yapp.presentation.ui.main

import com.yapp.core.base.BaseViewModel
import com.yapp.core.redux.BaseMiddleware
import com.yapp.core.redux.BaseSingleEvent
import com.yapp.core.redux.Reducer
import com.yapp.presentation.ui.main.redux.MainIntent
import com.yapp.presentation.ui.main.redux.MainMiddleware
import com.yapp.presentation.ui.main.redux.MainReducer
import com.yapp.presentation.ui.main.redux.MainSingleEvent
import com.yapp.presentation.ui.main.redux.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainMiddleware: MainMiddleware
): BaseViewModel<MainIntent, MainState, MainSingleEvent>() {
    init {
        start()
    }

    override fun registerMiddleware(): List<BaseMiddleware<MainIntent, MainSingleEvent>> {
        return listOf(mainMiddleware)
    }

    override fun registerReducer(): Reducer<MainState> {
        return MainReducer()
    }

    override fun processError(throwable: Throwable) {
        Timber.e(throwable.localizedMessage)
    }

    override fun getInitialState(): MainState {
        return MainState()
    }
}