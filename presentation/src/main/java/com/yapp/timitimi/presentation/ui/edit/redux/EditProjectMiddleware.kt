package com.yapp.timitimi.presentation.ui.edit.redux

import com.yapp.timitimi.redux.BaseMiddleware
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

class EditProjectMiddleware @Inject constructor(
) : BaseMiddleware<EditProjectIntent, EditProjectSingleEvent> {
    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<EditProjectIntent>,
        eventFlow: MutableSharedFlow<EditProjectSingleEvent>
    ): Flow<EditProjectIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<EditProjectIntent.ChangeProjectName>()
                    .onEach {
                        Timber.e(it.toString())
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<EditProjectIntent.ClickBackButton>()
                    .onEach {
                        eventFlow.emit(EditProjectSingleEvent.Exit)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<EditProjectIntent.CompleteEdit>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(EditProjectSingleEvent.NavigateToMain)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),
            )
        }
    }
}
