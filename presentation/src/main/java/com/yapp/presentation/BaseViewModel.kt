package com.yapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yapp.presentation.redux.BaseIntent
import com.yapp.presentation.redux.BaseMiddleware
import com.yapp.presentation.redux.BaseState
import com.yapp.presentation.redux.Reducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel<
        INTENT : BaseIntent,
        STATE : BaseState
        > : ViewModel() {

    private val mutableIntentFlow = MutableSharedFlow<INTENT>()
    val intentFlow: SharedFlow<INTENT>
        get() = mutableIntentFlow

    lateinit var viewState: StateFlow<STATE>

    fun dispatch(intent: INTENT) {
        viewModelScope.launch {
            mutableIntentFlow.emit(intent)
        }
    }

    private fun SharedFlow<INTENT>.mutate(): Flow<INTENT> {
        val middlewareList = registerMiddleware()
        return if (middlewareList.isEmpty()) {
            intentFlow
        } else {
            middlewareList
                .scan(intentFlow.filterNotNull()) { prevIntentFlow, nextMiddleware ->
                    merge(
                        nextMiddleware.mutate(viewModelScope, prevIntentFlow),
                        prevIntentFlow
                    )
                }.last()
        }
    }

    abstract fun registerMiddleware(): List<BaseMiddleware<INTENT>>
    abstract fun registerReducer(): Reducer<STATE>
    abstract fun processError(throwable: Throwable)
    abstract fun getInitialState(): STATE

    open fun start() {
        val initialViewState = getInitialState()
        viewState = intentFlow
            .mutate()
            .scan(initialViewState) { prevState, mutate ->
                registerReducer().invoke(mutate, prevState)
            }
            .catch { processError(it) }
            .stateIn(
                viewModelScope,
                // 바로 생산 시작.
                SharingStarted.Eagerly,
                initialViewState
            )
    }
}
