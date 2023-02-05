package com.yapp.timitimi.presentation.ui.splash

import androidx.lifecycle.viewModelScope
import com.yapp.timitimi.base.BaseViewModel
import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.presentation.ui.splash.redux.SplashIntent
import com.yapp.timitimi.presentation.ui.splash.redux.SplashMiddleware
import com.yapp.timitimi.presentation.ui.splash.redux.SplashReducer
import com.yapp.timitimi.presentation.ui.splash.redux.SplashSingleEvent
import com.yapp.timitimi.presentation.ui.splash.redux.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val middleware: SplashMiddleware,
    private val reducer: SplashReducer,
    private val userPreference: UserPreference
) : BaseViewModel<
        SplashIntent,
        SplashState,
        SplashSingleEvent>() {

    init {
        viewModelScope.launch {
            start()
            delay(500)
            validateAccessToken()
        }
    }

     private fun validateAccessToken() {
        if (userPreference.accessToken.isNotEmpty()) {
            dispatch(SplashIntent.GetAccessTokenSucceed)
        } else {
            //navigate to main screen
            dispatch(SplashIntent.GetAccessTokenFailed)
        }
    }

    fun renewAccessToken() {

    }

    override fun registerMiddleware() = listOf(middleware)

    override fun registerReducer() = reducer

    override fun processError(throwable: Throwable) {
        Timber.e(throwable.localizedMessage)
    }

    override fun getInitialState() = SplashState()
}