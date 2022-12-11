package com.yapp.presentation.ui.createproject

import androidx.lifecycle.viewModelScope
import com.yapp.core.base.BaseViewModel
import com.yapp.presentation.ui.createproject.base.CreateProjectIntent
import com.yapp.presentation.ui.createproject.base.CreateProjectMiddleware
import com.yapp.presentation.ui.createproject.base.CreateProjectReducer
import com.yapp.presentation.ui.createproject.base.CreateProjectSingleEvent
import com.yapp.presentation.ui.createproject.base.CreateProjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    private val middleware: CreateProjectMiddleware,
    private val reducer: CreateProjectReducer
) : BaseViewModel<CreateProjectIntent,
        CreateProjectState,
        CreateProjectSingleEvent>() {
    override fun registerMiddleware() = listOf(middleware)
    override fun registerReducer() = reducer

    override fun processError(throwable: Throwable) {
        Timber.e(throwable.localizedMessage)
    }

    override fun getInitialState(): CreateProjectState {
        return CreateProjectState()
    }

    fun clickProjectEndDate() {
        viewState.onEach {
            val intent = if (it.isNotInitializedStartDate()) {
                CreateProjectIntent.ShowToast("시작일을 먼저 지정해주세요.")
            } else {
                CreateProjectIntent.OpenDueDateCalendar(CreateProjectIntent.DueDateType.END)
            }
            dispatch(intent)
        }.launchIn(viewModelScope)
    }

    fun selectEndDate(
        day: Int,
        month: Int,
        year: Int,
    ) {
        viewState.onEach {
            val intent = if (it.isNotInitializedStartDate()) {
                //todo: 시작일과 비교하여, 미래날짜로 설정할 수 있게 강제화 해야함.
                CreateProjectIntent.ShowToast("테스트...")
            } else {
                CreateProjectIntent.SelectEndProjectDate(
                    day = day,
                    month = month,
                    year = year
                )
            }
            dispatch(intent)
        }.launchIn(viewModelScope)
    }

    init {
        start()
    }
}