package com.yapp.presentation.ui.createproject

import com.yapp.core.base.BaseViewModel
import com.yapp.presentation.ui.createproject.base.CreateProjectIntent
import com.yapp.presentation.ui.createproject.base.CreateProjectMiddleware
import com.yapp.presentation.ui.createproject.base.CreateProjectReducer
import com.yapp.presentation.ui.createproject.base.CreateProjectSingleEvent
import com.yapp.presentation.ui.createproject.base.CreateProjectState
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