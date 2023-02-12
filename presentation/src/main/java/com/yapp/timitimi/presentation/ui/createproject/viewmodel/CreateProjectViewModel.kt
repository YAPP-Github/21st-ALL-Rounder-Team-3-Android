package com.yapp.timitimi.presentation.ui.createproject.viewmodel

import androidx.lifecycle.viewModelScope
import com.yapp.timitimi.base.BaseViewModel
import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.presentation.ui.createproject.redux.CreateProjectIntent
import com.yapp.timitimi.presentation.ui.createproject.redux.CreateProjectMiddleware
import com.yapp.timitimi.presentation.ui.createproject.redux.CreateProjectReducer
import com.yapp.timitimi.presentation.ui.createproject.redux.CreateProjectSingleEvent
import com.yapp.timitimi.presentation.ui.createproject.redux.CreateProjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    private val userPreference: UserPreference,
    private val middleware: CreateProjectMiddleware,
    private val reducer: CreateProjectReducer
) : BaseViewModel<CreateProjectIntent,
        CreateProjectState,
        CreateProjectSingleEvent>() {
    private var currentProjectId = ""
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

    fun setCurrentProjectId(id: String) {
        currentProjectId = id
    }

    fun saveLastViewedProjectId() = viewModelScope.launch {
        viewModelScope.async {
            userPreference.lastViewedProjectId = currentProjectId
            Timber.e("lastViewedProjectId:" + userPreference.lastViewedProjectId)
        }.await()
    }

    fun shareUrl() {
        dispatch(CreateProjectIntent.ShareProjectDeeplink(projectId = currentProjectId))
    }
}