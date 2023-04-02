package com.yapp.timitimi.presentation.ui.intro.redux

import com.yapp.timitimi.domain.entity.LoginProviderInfo
import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.domain.respository.OAuthRepository
import com.yapp.timitimi.redux.BaseMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import timber.log.Timber
import javax.inject.Inject

class IntroMiddleware @Inject constructor(
    private val loginRepository: OAuthRepository,
    private val preference: UserPreference
) : BaseMiddleware<IntroIntent, IntroSingleEvent> {
    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<IntroIntent>,
        eventFlow: MutableSharedFlow<IntroSingleEvent>
    ): Flow<IntroIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<IntroIntent.Login>()
                    .onEach {
                        eventFlow.emit(IntroSingleEvent.TryKakaoLogin)
                    }
                    .shareIn(scope, SharingStarted.Eagerly),

                filterIsInstance<IntroIntent.KakaoLoginSucceed>()
                    .onEach {
                        loginRepository.login(
                            LoginProviderInfo(appToken = it.appToken, provider = "KAKAO")
                        )
                            .onEach { user ->
                                val userInfo = user.getOrNull()
                                userInfo?.let {
                                    preference.refreshToken = it.refreshToken
                                    preference.accessToken = it.appToken
                                    eventFlow.emit(IntroSingleEvent.NavigateToCreateProject)
                                }?: run {
                                    Timber.e("userInfo is null")
                                    eventFlow.emit(IntroSingleEvent.LoginFailed)
                                }
                            }
                            .launchIn(scope)
                    }
                    .shareIn(scope, SharingStarted.Eagerly),
            )
        }
    }
}
