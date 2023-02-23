package com.yapp.timitimi.presentation.ui.mypage

import androidx.lifecycle.viewModelScope
import com.yapp.timitimi.base.BaseViewModel
import com.yapp.timitimi.domain.entity.UserProfile
import com.yapp.timitimi.domain.respository.MemberRepository
import com.yapp.timitimi.presentation.ui.mypage.redux.MyPageIntent
import com.yapp.timitimi.presentation.ui.mypage.redux.MyPageMiddleware
import com.yapp.timitimi.presentation.ui.mypage.redux.MyPageReducer
import com.yapp.timitimi.presentation.ui.mypage.redux.MyPageSingleEvent
import com.yapp.timitimi.presentation.ui.mypage.redux.MyPageState
import com.yapp.timitimi.redux.BaseMiddleware
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageMiddleware: MyPageMiddleware,
    private val myPageReducer: MyPageReducer,
    private val memberRepository: MemberRepository
) : BaseViewModel<
        MyPageIntent,
        MyPageState,
        MyPageSingleEvent>() {
    override fun registerMiddleware(): List<BaseMiddleware<MyPageIntent, MyPageSingleEvent>> =
        listOf(myPageMiddleware)

    override fun registerReducer() = myPageReducer

    override fun processError(throwable: Throwable) {
        Timber.e(throwable)
    }

    override fun getInitialState(): MyPageState = MyPageState()

    init {
        start()
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            memberRepository.getUserInfo().onEach {
                dispatch(
                    MyPageIntent.LoadScreen(
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