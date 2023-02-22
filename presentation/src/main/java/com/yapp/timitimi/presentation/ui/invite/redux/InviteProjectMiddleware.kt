package com.yapp.timitimi.presentation.ui.invite.redux

import com.yapp.timitimi.redux.BaseMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class InviteProjectMiddleware @Inject constructor() : BaseMiddleware<InviteProjectIntent, InviteProjectSingleEvent> {
    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<InviteProjectIntent>,
        eventFlow: MutableSharedFlow<InviteProjectSingleEvent>
    ): Flow<InviteProjectIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<InviteProjectIntent.ClickBackButton>()
                    .onEach {
                        eventFlow.emit(InviteProjectSingleEvent.Exit)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<InviteProjectIntent.ClickCompleteButton>()
                    .onEach {
                        eventFlow.emit(InviteProjectSingleEvent.ValidateUrl)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),
            )
        }
    }
}
