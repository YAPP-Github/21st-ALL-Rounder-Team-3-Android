package com.yapp.timitimi.presentation.ui.splash.redux

import com.yapp.timitimi.redux.BaseMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import timber.log.Timber
import javax.inject.Inject

class SplashMiddleware @Inject constructor(
) : BaseMiddleware<SplashIntent, SplashSingleEvent> {
    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<SplashIntent>,
        eventFlow: MutableSharedFlow<SplashSingleEvent>
    ): Flow<SplashIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<SplashIntent.GetAccessTokenSucceed>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(SplashSingleEvent.NavigateToMain)
                    }
                    .shareIn(scope, SharingStarted.Eagerly),

            filterIsInstance<SplashIntent.GetAccessTokenFailed>()
                .onEach {
                    Timber.e(it.toString())
                    eventFlow.emit(SplashSingleEvent.NavigateToLogin)
                }
                .shareIn(scope, SharingStarted.Eagerly)
            )

            filterIsInstance<SplashIntent.NeedRefreshToken>()
                .onEach {
                    Timber.e("Need to change refresh token")
                    eventFlow.emit(SplashSingleEvent.RenewAccessToken)
                }
                .shareIn(scope, SharingStarted.Eagerly)
        }
    }
}
