package com.yapp.core.redux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

interface BaseMiddleware<INTENT : BaseIntent, EVENT: BaseSingleEvent> {
    fun mutate(scope: CoroutineScope, intentFlow: Flow<INTENT>, eventFlow: MutableSharedFlow<EVENT>): Flow<INTENT>
}
