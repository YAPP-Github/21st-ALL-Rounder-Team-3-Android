package com.yapp.timitimi.presentation.ui.mypage.edit

import androidx.lifecycle.viewModelScope
import com.yapp.timitimi.base.BaseViewModel
import com.yapp.timitimi.domain.entity.UserProfile
import com.yapp.timitimi.domain.respository.MemberRepository
import com.yapp.timitimi.presentation.ui.mypage.edit.redux.EditUserInfoIntent
import com.yapp.timitimi.presentation.ui.mypage.edit.redux.EditUserInfoMiddleware
import com.yapp.timitimi.presentation.ui.mypage.edit.redux.EditUserInfoReducer
import com.yapp.timitimi.presentation.ui.mypage.edit.redux.EditUserInfoSingleEvent
import com.yapp.timitimi.presentation.ui.mypage.edit.redux.EditUserInfoState
import com.yapp.timitimi.redux.BaseMiddleware
import com.yapp.timitimi.redux.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditUserInfoViewModel @Inject constructor(
    private val middleware: EditUserInfoMiddleware,
    private val reducer: EditUserInfoReducer,
    private val memberRepository: MemberRepository
) : BaseViewModel<
        EditUserInfoIntent,
        EditUserInfoState,
        EditUserInfoSingleEvent>() {

    override fun registerMiddleware(): List<BaseMiddleware<EditUserInfoIntent, EditUserInfoSingleEvent>> =
        listOf(middleware)

    override fun registerReducer(): Reducer<EditUserInfoState> = reducer

    override fun processError(throwable: Throwable) {
        Timber.e(throwable)
    }

    override fun getInitialState(): EditUserInfoState = EditUserInfoState()

    init {
        start()
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            memberRepository.getUserInfo().onEach {
                dispatch(
                    EditUserInfoIntent.LoadScreen(
                        isLoading = true,
                        userProfile = it.getOrDefault(
                            UserProfile.empty()
                        )
                    )
                )
            }
                .catch {

                }
                .launchIn(viewModelScope)
        }
    }
}