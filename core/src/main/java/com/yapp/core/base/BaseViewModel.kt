package com.yapp.core.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yapp.core.redux.BaseIntent
import com.yapp.core.redux.BaseMiddleware
import com.yapp.core.redux.BaseSingleEvent
import com.yapp.core.redux.BaseState
import com.yapp.core.redux.Reducer
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import timber.log.Timber

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

    abstract fun registerMiddleware(): List<BaseMiddleware<INTENT, EVENT>>
    abstract fun registerReducer(): Reducer<STATE>
    abstract fun processError(throwable: Throwable)
    open fun getInitialState(): STATE {
        start()
    }

    open fun start() {
        val initialViewState = getInitialState()
        viewState = _mutableIntentFlow
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
