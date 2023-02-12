@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.yapp.timitimi.presentation.ui.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.germainkevin.collapsingtopbar.rememberCollapsingTopBarScrollBehavior
import com.yapp.timitimi.presentation.ui.edit.EditProjectActivity
import com.yapp.timitimi.presentation.ui.main.MainActivity
import com.yapp.timitimi.presentation.ui.main.MainViewModel
import com.yapp.timitimi.presentation.ui.main.redux.MainIntent
import com.yapp.timitimi.presentation.ui.main.redux.MainSingleEvent
import com.yapp.timitimi.presentation.ui.main.screen.components.Header
import com.yapp.timitimi.presentation.ui.main.screen.components.TaskSection
import com.yapp.timitimi.theme.Gray200
import com.yapp.timitimi.ui.startActivityWithAnimation
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

const val Me = "상록" //TODO 내 정보에 대해 Toplevel로 관리할 지?

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val scrollBehavior = rememberCollapsingTopBarScrollBehavior()
    val activity = LocalContext.current as MainActivity

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
                        activity.startActivityWithAnimation<EditProjectActivity>()
                    }

                    MainSingleEvent.NavigateToTaskDetail -> {
                        //TODO(EvergreenTree97)
                    }
                }
            }
            .launchIn(this)
    }

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
            onClickEditIcon = { viewModel.dispatch(MainIntent.ClickEditButton) }
        )
        TaskSection(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Gray200)
                .offset {
                    IntOffset(x = 0, y = 8)
                }
                .padding(horizontal = 16.dp)
                .padding(top = 22.dp),
            state = state,
            isProfileSelected = state.selectedProfileIndex > 0,
        )
    }
}