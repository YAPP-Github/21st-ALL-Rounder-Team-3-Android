package com.yapp.timitimi.presentation.ui.main.redux

import com.yapp.timitimi.redux.BaseMiddleware
import com.yapp.timitimi.redux.BaseSingleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import timber.log.Timber
import javax.inject.Inject

class MainMiddleware @Inject constructor() : BaseMiddleware<MainIntent, MainSingleEvent> {

    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<MainIntent>,
        eventFlow: MutableSharedFlow<MainSingleEvent>
    ): Flow<MainIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<MainIntent.OnClickBackButton>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(MainSingleEvent.NavigateToProjectList)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.OnClickEditButton>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(MainSingleEvent.NavigateToEditProject)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.OnClickNotificationButton>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(MainSingleEvent.NavigateToNotificationList)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.OnSelectProfile>()
                    .onEach {
                        Timber.e(it.toString())
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.OnSelectAddProfile>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(MainSingleEvent.NavigateToInviteMember)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.OnClickFab>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(MainSingleEvent.NavigateToCreateTask)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

            )
        }
    }
}
