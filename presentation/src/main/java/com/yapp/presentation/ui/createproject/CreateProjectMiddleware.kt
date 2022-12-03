package com.yapp.presentation.ui.createproject

import com.yapp.core.redux.BaseMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
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
) : BaseMiddleware<CreateProjectIntent> {
    override fun mutate(scope: CoroutineScope, intentFlow: Flow<CreateProjectIntent>): Flow<CreateProjectIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<CreateProjectIntent.ChangeProjectName>()
                    .onEach {
                        Timber.e(it.toString())
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),
            )
        }
    }
}
