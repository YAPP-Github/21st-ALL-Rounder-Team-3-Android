package com.yapp.presentation.ui.createproject.viewmodel

import com.yapp.core.base.BaseViewModel
import com.yapp.presentation.ui.createproject.redux.CreateProjectIntent
import com.yapp.presentation.ui.createproject.redux.CreateProjectMiddleware
import com.yapp.presentation.ui.createproject.redux.CreateProjectReducer
import com.yapp.presentation.ui.createproject.redux.CreateProjectSingleEvent
import com.yapp.presentation.ui.createproject.redux.CreateProjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    private val middleware: CreateProjectMiddleware,
    private val reducer: CreateProjectReducer
) : BaseViewModel<CreateProjectIntent,
        CreateProjectState,
        CreateProjectSingleEvent>() {
    override fun registerMiddleware() = listOf(middleware)
    override fun registerReducer() = reducer

    override fun processError(throwable: Throwable) {
        Timber.e(throwable.localizedMessage)
    }

    override fun getInitialState(): CreateProjectState {
        return CreateProjectState()
    }

    init {
        start()
    }
}