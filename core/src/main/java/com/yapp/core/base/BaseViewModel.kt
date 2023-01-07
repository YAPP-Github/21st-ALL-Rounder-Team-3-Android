package com.yapp.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yapp.core.redux.BaseIntent
import com.yapp.core.redux.BaseMiddleware
import com.yapp.core.redux.BaseSingleEvent
import com.yapp.core.redux.BaseState
import com.yapp.core.redux.Reducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel<
        INTENT : BaseIntent,
        STATE : BaseState,
        EVENT: BaseSingleEvent
        > : ViewModel() {

    private val _mutableIntentFlow = MutableSharedFlow<INTENT>()
    val intentFlow: SharedFlow<INTENT> = _mutableIntentFlow.asSharedFlow()

    private val _singleEventFlow = MutableSharedFlow<EVENT>()
    val singleEventFlow: SharedFlow<EVENT> = _singleEventFlow.asSharedFlow()

    lateinit var viewState: StateFlow<STATE>

    fun dispatch(intent: INTENT) {
        viewModelScope.launch {
            _mutableIntentFlow.emit(intent)
        }
    }

    private fun SharedFlow<INTENT>.mutate(): Flow<INTENT> {
        val middlewareList = registerMiddleware()
        return if (middlewareList.isEmpty()) {
            this
        } else {
            middlewareList
                .scan(this.filterNotNull()) { prevIntentFlow, nextMiddleware ->
                    merge(
                        nextMiddleware.mutate(viewModelScope, prevIntentFlow, _singleEventFlow),
                        prevIntentFlow
                    )
                }.last()
        }
    }

    open fun registerMiddleware(): List<BaseMiddleware<INTENT, EVENT>> = emptyList()
    open fun registerReducer(): Reducer<STATE>? = null
    abstract fun processError(throwable: Throwable)
    abstract fun getInitialState(): STATE

    open fun start() {
        val initialViewState = getInitialState()
        viewState = _mutableIntentFlow
            .mutate()
            .filter { registerReducer() != null }
            .scan(initialViewState) { prevState, mutate ->
                registerReducer()!!.invoke(mutate, prevState)
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
