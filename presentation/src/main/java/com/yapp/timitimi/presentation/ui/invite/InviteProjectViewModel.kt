package com.yapp.timitimi.presentation.ui.invite

import com.yapp.timitimi.base.BaseViewModel
import com.yapp.timitimi.presentation.ui.invite.redux.InviteProjectIntent
import com.yapp.timitimi.presentation.ui.invite.redux.InviteProjectMiddleware
import com.yapp.timitimi.presentation.ui.invite.redux.InviteProjectReducer
import com.yapp.timitimi.presentation.ui.invite.redux.InviteProjectSingleEvent
import com.yapp.timitimi.presentation.ui.invite.redux.InviteProjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InviteProjectViewModel @Inject constructor(
    private val middleware: InviteProjectMiddleware,
    private val reducer: InviteProjectReducer
): BaseViewModel<InviteProjectIntent,
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

    init {
        start()
    }
}