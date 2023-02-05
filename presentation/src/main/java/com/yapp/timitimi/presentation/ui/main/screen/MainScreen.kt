@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.yapp.timitimi.presentation.ui.main.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.germainkevin.collapsingtopbar.rememberCollapsingTopBarScrollBehavior
import com.yapp.timitimi.component.TaskType
import com.yapp.timitimi.kotlin.immutableFilter
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.ui.main.MainViewModel
import com.yapp.timitimi.presentation.ui.main.redux.MainIntent
import com.yapp.timitimi.presentation.ui.main.redux.MainSingleEvent
import com.yapp.timitimi.presentation.ui.main.screen.components.Header
import com.yapp.timitimi.presentation.ui.main.screen.components.taskContent
import com.yapp.timitimi.theme.Gray200
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    val scrollBehavior = rememberCollapsingTopBarScrollBehavior()

    LaunchedEffect(viewModel.singleEventFlow) {
        viewModel.singleEventFlow
            .onEach { event ->
                when (event) {
                    MainSingleEvent.NavigateToProjectList -> {
                        onBackPressed()
                    }

                    MainSingleEvent.NavigateToCreateTask -> {
                        //TODO(EvergreenTree97)
                    }

                    MainSingleEvent.NavigateToInviteMember -> {
                        //TODO(EvergreenTree97)
                    }

                    MainSingleEvent.NavigateToNotificationList -> {
                        //TODO(EvergreenTree97)
                    }

                    MainSingleEvent.NavigateToEditProject -> {
                        //TODO(EvergreenTree97)
                    }

                    MainSingleEvent.NavigateToTaskDetail -> {
                        //TODO(EvergreenTree97)
                    }
                }
            }
            .launchIn(this)
    }

    /* //TODO(EvergreenTree97) : 참여자 정보 불러오기
    LaunchedEffect(key1 = Unit) {
        viewModel.dispatch(MainIntent.Init("1"))
    }*/

    Column(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(color = Gray200)
    ) {
        Header(
            scrollBehavior = scrollBehavior,
            title = state.project.name,
            memo = state.project.memo,
            startDate = state.project.startDate,
            endDate = state.project.endDate,
            dDay = state.project.dDay,
            notificationCount = state.notificationCount,
            selectedProfileIndex = state.selectedProfileIndex,
            members = state.members,
            onProfileSelected = { viewModel.dispatch(MainIntent.SelectProfile(it)) },
        )
<<<<<<< Updated upstream
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray200)
            .offset {
                IntOffset(x = 0, y = 8)
            }
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        taskContent(
            title = "나의 할일",
            tasks = state.tasks,
            isHide = false,
            onClick = {}
        )
=======

        val myTasks by remember {
            derivedStateOf {
                state.tasks.immutableFilter { it.member.name == "상록" }
            }
        }
        val otherTasks by remember {
            derivedStateOf {
                state.tasks.immutableFilter { it.member.name != "상록" }
            }
        }
        val doneTasks by remember {
            derivedStateOf {
                state.tasks.immutableFilter { it.taskType is TaskType.Done }
            }
        }
        val progressInTasks by remember {
            derivedStateOf {
                state.tasks.immutableFilter { it.member.name == state.members[state.selectedProfileIndex].name }
            }
        }

        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Gray200)
                .offset {
                    IntOffset(x = 0, y = 8)
                }
                .padding(horizontal = 34.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            when (state.selectedProfileIndex) {
                0 -> {
                    taskContent(
                        title = "나의 할일",
                        tasks = myTasks,
                        onClick = {}
                    )
                    taskContent(
                        title = "다른 팀원 업무",
                        tasks = otherTasks,
                        onClick = {}
                    )
                    taskContent(
                        title = "완료된 업무",
                        tasks = doneTasks,
                        onClick = {}
                    )
                }

                else -> {

                    taskContent(
                        title = "진행 중인 업무",
                        tasks = progressInTasks,
                        isHide = false,
                        onClick = {}
                    )
                }
            }
        }
>>>>>>> Stashed changes
    }
}
}
