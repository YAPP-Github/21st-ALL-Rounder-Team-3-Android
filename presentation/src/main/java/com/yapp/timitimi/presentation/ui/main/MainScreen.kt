@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.yapp.timitimi.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.germainkevin.collapsingtopbar.rememberCollapsingTopBarScrollBehavior
import com.yapp.timitimi.presentation.ui.main.components.Header
import com.yapp.timitimi.presentation.ui.main.components.taskContent
import com.yapp.timitimi.presentation.ui.main.redux.MainIntent
import com.yapp.timitimi.presentation.ui.main.redux.MainSingleEvent
import com.yapp.timitimi.theme.Gray200
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

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

    LaunchedEffect(key1 = Unit) {
        viewModel.dispatch(MainIntent.Init("1"))
    }

    val scrollBehavior = rememberCollapsingTopBarScrollBehavior()
    Column(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(color = Gray200)
    ) {
        Header(
            scrollBehavior = scrollBehavior,
            title = "고전문학사 팀플 3조",
            memo = "학기 성적 A+ 도전 도전 도전 도전🎃",
            startDate = "11.16",
            endDate = "12.7",
            notificationCount = 2,
            selectedProfileIndex = state.selectedProfileIndex,
            members = state.members,
            onProfileSelected = { viewModel.dispatch(MainIntent.OnSelectProfile(it)) },
        )
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
        }
    }
}
