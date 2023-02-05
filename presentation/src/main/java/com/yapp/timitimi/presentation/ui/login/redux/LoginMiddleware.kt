package com.yapp.timitimi.presentation.ui.login.redux

import com.yapp.timitimi.domain.preference.UserPreference
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

class LoginMiddleware @Inject constructor(
    private val userPreference: UserPreference,
) : BaseMiddleware<LoginIntent, LoginSingleEvent> {
    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<LoginIntent>,
        eventFlow: MutableSharedFlow<LoginSingleEvent>
    ): Flow<LoginIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<LoginIntent.KakaoLoginFailed>()
                    .onEach {
                        eventFlow.emit(LoginSingleEvent.ShowToast("로그인 실패"))
                        eventFlow.emit(LoginSingleEvent.Finish)
                    }
                    .shareIn(scope, SharingStarted.Eagerly),

                filterIsInstance<LoginIntent.KakaoLoginSucceed>()
                    .onEach {
                        userPreference.accessToken = it.appToken
                        Timber.e("저장된 accessToken: ${userPreference.accessToken}")
                        eventFlow.emit(LoginSingleEvent.ShowToast("로그인 성공"))
                        eventFlow.emit(LoginSingleEvent.NavigateToCreateProject)
                    }
                    .shareIn(scope, SharingStarted.Eagerly),
            )
        }
    }
}
