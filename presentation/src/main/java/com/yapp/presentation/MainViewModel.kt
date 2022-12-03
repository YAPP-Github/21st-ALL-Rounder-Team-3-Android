package com.yapp.presentation

import com.yapp.presentation.redux.BaseMiddleware
import com.yapp.presentation.redux.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainMiddleware: MainMiddleware
): BaseViewModel<MainIntent, MainState>() {
    init {
        start()
    }

    override fun registerMiddleware(): List<BaseMiddleware<MainIntent>> {
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