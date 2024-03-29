package com.yapp.timitimi.presentation.ui.splash

import com.yapp.timitimi.base.BaseViewModel
import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.domain.respository.MemberRepository
import com.yapp.timitimi.domain.respository.OAuthRepository
import com.yapp.timitimi.presentation.ui.splash.redux.SplashIntent
import com.yapp.timitimi.presentation.ui.splash.redux.SplashMiddleware
import com.yapp.timitimi.presentation.ui.splash.redux.SplashReducer
import com.yapp.timitimi.presentation.ui.splash.redux.SplashSingleEvent
import com.yapp.timitimi.presentation.ui.splash.redux.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val middleware: SplashMiddleware,
    private val reducer: SplashReducer,
    private val userPreference: UserPreference,
    private val memberRepository: MemberRepository,
    private val oAuthRepository: OAuthRepository
) : BaseViewModel<
        SplashIntent,
        SplashState,
        SplashSingleEvent>() {

    private var invitedProjectId = ""

    init {
        start()
    }

    suspend fun initLogin() {
        if (userPreference.accessToken.isNotEmpty()) {
            validateAccessToken()
        } else {
            //navigate to main screen
            dispatch(SplashIntent.GetAccessTokenFailed)
        }
    }

    private suspend fun validateAccessToken() {
        kotlin.runCatching {
            memberRepository.getUserInfo()
        }.onSuccess {
            if (invitedProjectId.isEmpty()) {
                dispatch(SplashIntent.GetAccessTokenSucceed)
            } else {
                dispatch(SplashIntent.GetAccessTokenSucceedAndInvitedUser(invitedProjectId))
            }
        }.onFailure {
            dispatch(SplashIntent.NeedRefreshToken)
        }
    }

    suspend fun renewAccessToken() {
        oAuthRepository.refreshUserToken()
    }

    fun setInvitedProjectId(id: String) {
        invitedProjectId = id
    }

    override fun registerMiddleware() = listOf(middleware)

    override fun registerReducer() = reducer

    override fun processError(throwable: Throwable) {
        Timber.e(throwable.localizedMessage)
    }

    override fun getInitialState() = SplashState()
}