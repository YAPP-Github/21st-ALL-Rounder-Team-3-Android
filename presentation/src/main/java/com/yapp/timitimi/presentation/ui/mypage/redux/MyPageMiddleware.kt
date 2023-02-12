package com.yapp.timitimi.presentation.ui.mypage.redux

import com.yapp.timitimi.domain.respository.MemberRepository
import com.yapp.timitimi.redux.BaseMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class MyPageMiddleware @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseMiddleware<MyPageIntent, MyPageSingleEvent> {
    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<MyPageIntent>,
        eventFlow: MutableSharedFlow<MyPageSingleEvent>
    ): Flow<MyPageIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<MyPageIntent.LoadScreen>()
                    .onEach {

                    }
                    .shareIn(scope, SharingStarted.Eagerly),

                filterIsInstance<MyPageIntent.ClickEditMyInfo>()
                    .onEach {
                        eventFlow.emit(MyPageSingleEvent.NavigateToProfile)
                    }
                    .shareIn(scope, SharingStarted.Eagerly),
                )
        }
    }
}
