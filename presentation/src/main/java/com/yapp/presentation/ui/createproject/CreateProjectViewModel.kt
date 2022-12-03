package com.yapp.presentation.ui.createproject

import com.yapp.core.base.BaseViewModel
import com.yapp.core.redux.BaseMiddleware
import com.yapp.core.redux.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    private val middleware: CreateProjectMiddleware,
    private val reducer: CreateProjectReducer
) : BaseViewModel<CreateProjectIntent, CreateProjectState>() {
    override fun registerMiddleware(): List<BaseMiddleware<CreateProjectIntent>> {
        return listOf(middleware)
    }

    override fun registerReducer(): Reducer<CreateProjectState> {
        return reducer
    }

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