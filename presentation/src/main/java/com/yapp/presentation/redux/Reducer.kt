package com.yapp.presentation.redux

interface Reducer<State> {
    fun invoke(action: BaseIntent, state: State): State
}

