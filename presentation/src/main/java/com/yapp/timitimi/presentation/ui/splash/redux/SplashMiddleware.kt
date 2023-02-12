package com.yapp.timitimi.presentation.ui.splash.redux

import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.domain.respository.ProjectsRepository
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

class SplashMiddleware @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    private val userPreference: UserPreference
) : BaseMiddleware<SplashIntent, SplashSingleEvent> {
    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<SplashIntent>,
        eventFlow: MutableSharedFlow<SplashSingleEvent>
    ): Flow<SplashIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<SplashIntent.GetAccessTokenSucceedAndInvitedUser>()
                    .onEach {
                        userPreference.lastViewedProjectId = it.invitedId
                        eventFlow.emit(SplashSingleEvent.NavigateToMain)
                    }
                    .shareIn(scope, SharingStarted.Eagerly),

                filterIsInstance<SplashIntent.GetAccessTokenSucceed>()
                    .onEach {
                        projectsRepository.getAllProject().onEach { projects ->
                            if (projects.isEmpty()) {
                                eventFlow.emit(SplashSingleEvent.NavigateToCreateProject)
                            } else {
                                eventFlow.emit(SplashSingleEvent.NavigateToMain)
                            }
                        }.launchIn(scope)
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
