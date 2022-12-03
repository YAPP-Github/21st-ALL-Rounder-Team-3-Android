package com.yapp.presentation

import com.yapp.presentation.redux.BaseMiddleware
import com.yapp.presentation.redux.Reducer
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(): BaseViewModel<
        MainIntent, MainState>() {

    override fun registerMiddleware(): List<BaseMiddleware<MainIntent>> {
        return listOf(MainMiddleware())
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

    init {
        start()
    }
}