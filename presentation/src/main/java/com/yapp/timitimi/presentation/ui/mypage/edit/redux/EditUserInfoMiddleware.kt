package com.yapp.timitimi.presentation.ui.mypage.edit.redux

import com.yapp.timitimi.domain.entity.UserProfile
import com.yapp.timitimi.domain.respository.MemberRepository
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
import javax.inject.Inject

class EditUserInfoMiddleware @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseMiddleware<EditUserInfoIntent, EditUserInfoSingleEvent> {
    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<EditUserInfoIntent>,
        eventFlow: MutableSharedFlow<EditUserInfoSingleEvent>
    ): Flow<EditUserInfoIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<EditUserInfoIntent.ClickUserProfileImageChanged>()
                    .onEach {
                        eventFlow.emit(EditUserInfoSingleEvent.ShowChangeProfileBottomSheet)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<EditUserInfoIntent.ClickBackButton>()
                    .onEach {
                        eventFlow.emit(EditUserInfoSingleEvent.NavigateToBackScreen)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<EditUserInfoIntent.ClickDefaultProfileImageSection>()
                    .onEach {
                        memberRepository.getUserInfo()
                            .onEach {
                                EditUserInfoIntent.RevertDefaultUserProfileImage(
                                    it.getOrDefault(
                                        UserProfile.empty()
                                    ).imageUrl
                                )
                            }
                            .launchIn(scope)
                        eventFlow.emit(EditUserInfoSingleEvent.DismissBottomSheet)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<EditUserInfoIntent.ClickTimiTimiImageChanged>()
                    .onEach {
                        eventFlow.emit(EditUserInfoSingleEvent.ShowChangeTimiTimiImageBottomSheet)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                )
        }
    }
}
