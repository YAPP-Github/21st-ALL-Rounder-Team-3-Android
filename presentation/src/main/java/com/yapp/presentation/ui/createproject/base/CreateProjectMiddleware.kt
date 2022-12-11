package com.yapp.presentation.ui.createproject.base

import com.yapp.core.redux.BaseMiddleware
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

class CreateProjectMiddleware @Inject constructor(
    // example
    // private val usecase: MainUseCase
) : BaseMiddleware<CreateProjectIntent, CreateProjectSingleEvent> {
    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<CreateProjectIntent>,
        eventFlow: MutableSharedFlow<CreateProjectSingleEvent>
    ): Flow<CreateProjectIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<CreateProjectIntent.ChangeProjectName>()
                    .onEach {
                        Timber.e(it.toString())
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<CreateProjectIntent.ClickBackButton>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(CreateProjectSingleEvent.Exit)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<CreateProjectIntent.ShowToast>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(CreateProjectSingleEvent.ShowToast(it.msg))
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<CreateProjectIntent.ClickNextButton>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(CreateProjectSingleEvent.NavigateToTwoStepPage)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<CreateProjectIntent.ClickAppBarBackButton>()
                    .onEach {
                        val event = if (it.progress == 1f) {
                            CreateProjectSingleEvent.NavigateOneStopPage
                        } else {
                            CreateProjectSingleEvent.Exit
                        }
                        Timber.e(event.toString())
                        eventFlow.emit(event)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),
                )
        }
    }
}
