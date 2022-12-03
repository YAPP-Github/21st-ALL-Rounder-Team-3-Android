package com.yapp.presentation

import com.yapp.presentation.redux.BaseMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
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
) : BaseMiddleware<MainIntent> {
    override fun mutate(scope: CoroutineScope, intentFlow: Flow<MainIntent>): Flow<MainIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<MainIntent.CompleteLoading>()
                    .onEach {
                        Timber.e("MainIntent.CompleteLoading")
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),
                )
        }
    }
}
