package com.yapp.timitimi.presentation.ui.mypage.edit.redux

import com.yapp.timitimi.redux.BaseMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class EditUserInfoMiddleware @Inject constructor(
) : BaseMiddleware<EditUserInfoIntent, EditUserInfoSingleEvent> {
    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<EditUserInfoIntent>,
        eventFlow: MutableSharedFlow<EditUserInfoSingleEvent>
    ): Flow<EditUserInfoIntent> {
        return intentFlow.run {
            merge()
        }
    }
}
