package com.yapp.core.redux

interface Reducer<State> {
    fun invoke(action: BaseIntent, state: State): State
}

