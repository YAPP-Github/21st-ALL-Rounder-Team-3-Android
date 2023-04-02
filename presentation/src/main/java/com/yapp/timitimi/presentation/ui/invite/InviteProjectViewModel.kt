package com.yapp.timitimi.presentation.ui.invite

import androidx.lifecycle.viewModelScope
import com.yapp.timitimi.base.BaseViewModel
import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.domain.respository.ParticipantsRepository
import com.yapp.timitimi.presentation.ui.invite.redux.InviteProjectIntent
import com.yapp.timitimi.presentation.ui.invite.redux.InviteProjectMiddleware
import com.yapp.timitimi.presentation.ui.invite.redux.InviteProjectReducer
import com.yapp.timitimi.presentation.ui.invite.redux.InviteProjectSingleEvent
import com.yapp.timitimi.presentation.ui.invite.redux.InviteProjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InviteProjectViewModel @Inject constructor(
    private val middleware: InviteProjectMiddleware,
    private val reducer: InviteProjectReducer,
    private val participantsRepository: ParticipantsRepository,
    private val userPreference: UserPreference
) : BaseViewModel<InviteProjectIntent,
        InviteProjectState,
        InviteProjectSingleEvent>() {

    override fun registerMiddleware() = listOf(middleware)
    override fun registerReducer() = reducer

    override fun processError(throwable: Throwable) {
        Timber.e(throwable.localizedMessage)
    }

    override fun getInitialState(): InviteProjectState {
        return InviteProjectState()
    }

    fun participateProject(projectId: String) = viewModelScope.launch {
        participantsRepository.postParticipants(projectId)
            .onEach {
                    userPreference.lastViewedProjectId = projectId
                }
            .catch {
                Timber.e("유효하지 않은 Project Id")
            }
            .launchIn(viewModelScope)
    }

    init {
        start()
    }
}