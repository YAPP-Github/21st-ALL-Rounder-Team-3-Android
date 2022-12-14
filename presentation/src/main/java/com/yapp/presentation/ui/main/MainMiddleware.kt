package com.yapp.presentation.ui.main

import com.yapp.core.redux.BaseMiddleware
import com.yapp.core.redux.BaseSingleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import timber.log.Timber
import javax.inject.Inject

class MainMiddleware @Inject constructor(
    // example
    // private val usecase: MainUseCase
) : BaseMiddleware<MainIntent, BaseSingleEvent> {
    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<MainIntent>,
        eventFlow: MutableSharedFlow<BaseSingleEvent>
    ): Flow<MainIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<MainIntent.ChangeTopButtonText>()
                    .onEach {
                        Timber.e(it.toString())
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.ChangeBottomButtonText>()
                    .onEach {
                        Timber.e(it.toString())
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),
            )
        }
    }
}
