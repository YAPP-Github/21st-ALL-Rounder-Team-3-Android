package com.yapp.timitimi.presentation.ui.intro

import com.yapp.timitimi.base.BaseViewModel
import com.yapp.timitimi.presentation.ui.intro.kakao.KakaoLoginState
import com.yapp.timitimi.presentation.ui.intro.redux.IntroIntent
import com.yapp.timitimi.presentation.ui.intro.redux.IntroMiddleware
import com.yapp.timitimi.presentation.ui.intro.redux.IntroReducer
import com.yapp.timitimi.presentation.ui.intro.redux.IntroSingleEvent
import com.yapp.timitimi.redux.BaseMiddleware
import com.yapp.timitimi.redux.BaseState
import com.yapp.timitimi.redux.EmptyViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val middleware: IntroMiddleware,
    private val reducer: IntroReducer
): BaseViewModel<
        IntroIntent,
        BaseState,
        IntroSingleEvent>() {

    override fun registerMiddleware(): List<BaseMiddleware<IntroIntent, IntroSingleEvent>> {
        return listOf(middleware)
    }

    override fun registerReducer() = reducer

    override fun processError(throwable: Throwable) {
        Timber.e(throwable.localizedMessage)
    }

    override fun getInitialState(): BaseState = EmptyViewState

    init {
        start()
    }

    fun login(state: KakaoLoginState) {
        when(state) {
            is KakaoLoginState.Succeed -> {
                val accessToken = state.token.accessToken
                dispatch(IntroIntent.KakaoLoginSucceed(accessToken))
            }

            is KakaoLoginState.Error -> {
                dispatch(IntroIntent.KakaoLoginFailed)
            }
        }
    }
}