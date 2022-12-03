package com.yapp.presentation.ui.main

import com.yapp.core.base.BaseViewModel
import com.yapp.core.redux.BaseMiddleware
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

    override fun registerReducer(): com.yapp.core.redux.Reducer<MainState> {
        return MainReducer()
    }

    override fun processError(throwable: Throwable) {
        Timber.e(throwable.localizedMessage)
    }

    override fun getInitialState(): MainState {
        return MainState()
    }
}