package com.yapp.core.redux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface BaseMiddleware<INTENT : BaseIntent> {
    fun mutate(scope: CoroutineScope, intentFlow: Flow<INTENT>): Flow<INTENT>
}
