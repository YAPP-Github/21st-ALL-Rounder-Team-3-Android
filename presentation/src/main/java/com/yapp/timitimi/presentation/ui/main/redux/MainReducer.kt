package com.yapp.timitimi.presentation.ui.main.redux

import com.yapp.timitimi.component.TaskType
import com.yapp.timitimi.redux.BaseIntent
import com.yapp.timitimi.redux.Reducer
import kotlinx.collections.immutable.persistentListOf

class MainReducer : Reducer<MainState> {
    override fun invoke(action: BaseIntent, state: MainState): MainState {
        return when (action) {
            is MainIntent.Init -> {
                state.copy(
                    /* members = dummyMembers,
                     tasks = dummyTasks,*/
                )
            }

            is MainIntent.SelectProfile -> {
                state.copy(
                    selectedProfileIndex = action.index
                )
            }

            MainIntent.ClickGuideScreen -> {
                state.copy(
                    currentStep = state.currentStep.plus(1)
                )
            }

            else -> {
                state.copy()
            }
        }
    }
}

val dummyMembers = persistentListOf(
    MainState.Member(
        true,
        "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        "상록"
    ),
    MainState.Member(
        false,
        "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        "세희"
    ),
    MainState.Member(
        false,
        "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        "정현"
    ),
)

val dummyTasks = persistentListOf(
    MainState.Task(
        taskType = TaskType.Request(
            confirmationPeriod = 3,
            currentConfirmation = 5,
            totalConfirmation = 6,
        ),
        id = 1,
        member = MainState.Member(
            true,
            "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
            "상록"
        ),
        startDate = "11.27",
        endDate = "11.29",
        dDay = "D-10",
        title = "DBPia, RISS 논문 리서치",
        memo = "송강과 노계 비교 분석해서 다뤄주기",
        completionCount = 5,
        totalCount = 6,
    ),
    MainState.Task(
        taskType = TaskType.Progress,
        member = MainState.Member(
            false,
            "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
            "세희"
        ),
        id = 2,
        startDate = "12.21",
        endDate = "12.29",
        dDay = "D-10",
        title = "C프로그래밍 기본",
        memo = "포인터가 무엇이고, 예제 실습하기",
    ),
    MainState.Task(
        taskType = TaskType.Done(
            completedTask = 5,
            notYetTask = 6
        ),
        id = 3,
        member = MainState.Member(
            false,
            "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
            "정현"
        ),
        startDate = "12.21",
        endDate = "12.29",
        dDay = "D-10",
        title = "자바 프로그래밍의 기본중의 기본",
        memo = "객체지향 프로그래밍에 대해 자세히 조사하기",
    ),
)
