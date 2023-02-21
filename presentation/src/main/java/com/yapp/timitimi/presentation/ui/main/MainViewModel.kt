package com.yapp.timitimi.presentation.ui.main

import com.yapp.timitimi.base.BaseViewModel
import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.presentation.ui.main.redux.MainIntent
import com.yapp.timitimi.presentation.ui.main.redux.MainMiddleware
import com.yapp.timitimi.presentation.ui.main.redux.MainReducer
import com.yapp.timitimi.presentation.ui.main.redux.MainSingleEvent
import com.yapp.timitimi.presentation.ui.main.redux.MainState
import com.yapp.timitimi.redux.BaseMiddleware
import com.yapp.timitimi.redux.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainMiddleware: MainMiddleware,
    private val userPreference: UserPreference
) : BaseViewModel<MainIntent, MainState, MainSingleEvent>() {
    init {
        start()
        dispatch(MainIntent.Init(userPreference.lastViewedProjectId.toInt()))
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