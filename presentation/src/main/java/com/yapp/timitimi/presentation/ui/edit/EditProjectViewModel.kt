package com.yapp.timitimi.presentation.ui.edit

import androidx.lifecycle.SavedStateHandle
import com.yapp.timitimi.base.BaseViewModel
import com.yapp.timitimi.presentation.constant.Extras
import com.yapp.timitimi.presentation.ui.edit.redux.EditProjectIntent
import com.yapp.timitimi.presentation.ui.edit.redux.EditProjectMiddleware
import com.yapp.timitimi.presentation.ui.edit.redux.EditProjectReducer
import com.yapp.timitimi.presentation.ui.edit.redux.EditProjectSingleEvent
import com.yapp.timitimi.presentation.ui.edit.redux.EditProjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditProjectViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val middleware: EditProjectMiddleware,
    private val reducer: EditProjectReducer
) : BaseViewModel<EditProjectIntent,
        EditProjectState,
        EditProjectSingleEvent>() {
    override fun registerMiddleware() = listOf(middleware)
    override fun registerReducer() = reducer

    override fun processError(throwable: Throwable) {
        Timber.e(throwable.localizedMessage)
    }

    override fun getInitialState(): EditProjectState {
        return EditProjectState()
    }

    init {
        start()
        val projectId = savedStateHandle.getStateFlow(Extras.ProjectId, -1).value
        if (projectId != -1) {
            dispatch(EditProjectIntent.GetProject(projectId))
        }else{
            throw IllegalStateException("unexpected project id")
        }
    }
}