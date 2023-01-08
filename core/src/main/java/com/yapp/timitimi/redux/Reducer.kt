package com.yapp.timitimi.redux

interface Reducer<State> {
    fun invoke(action: BaseIntent, state: State): State
}

