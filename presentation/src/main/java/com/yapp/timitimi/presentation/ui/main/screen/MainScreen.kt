@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.yapp.timitimi.presentation.ui.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.germainkevin.collapsingtopbar.rememberCollapsingTopBarScrollBehavior
import com.yapp.timitimi.presentation.constant.Extras
import com.yapp.timitimi.presentation.ui.edit.EditProjectActivity
import com.yapp.timitimi.presentation.ui.invite.InviteUserActivity
import com.yapp.timitimi.presentation.ui.main.MainActivity
import com.yapp.timitimi.presentation.ui.main.MainViewModel
import com.yapp.timitimi.presentation.ui.main.redux.MainIntent
import com.yapp.timitimi.presentation.ui.main.redux.MainSingleEvent
import com.yapp.timitimi.presentation.ui.main.screen.components.Header
import com.yapp.timitimi.presentation.ui.main.screen.components.TaskSection
import com.yapp.timitimi.presentation.web.createtask.CreateTaskActivity
import com.yapp.timitimi.presentation.web.taskdetail.TaskDetailActivity
import com.yapp.timitimi.theme.Gray200
import com.yapp.timitimi.theme.Purple500
import com.yapp.timitimi.ui.startActivityWithAnimation
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

const val Me = "상록" //TODO 내 정보에 대해 Toplevel로 관리할 지?

@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val scrollBehavior = rememberCollapsingTopBarScrollBehavior()
    val activity = LocalContext.current as MainActivity

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.loadData()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(viewModel.singleEventFlow) {
        viewModel.singleEventFlow
            .onEach { event ->
                when (event) {
                    MainSingleEvent.NavigateToProjectList -> {
                        onBackPressed()
                    }

                    is MainSingleEvent.NavigateToCreateTask -> {
                        activity.startActivityWithAnimation<CreateTaskActivity>(
                            intentBuilder = {
                                putExtra(Extras.ProjectId, event.projectId)
                            }
                        )
                    }

                    MainSingleEvent.NavigateToInviteMember -> {
                        activity.startActivityWithAnimation<InviteUserActivity>()
                    }

                    MainSingleEvent.NavigateToNotificationList -> {
                        //TODO(EvergreenTree97)
                    }

                    is MainSingleEvent.NavigateToEditProject -> {
                        activity.startActivityWithAnimation<EditProjectActivity>(
                            intentBuilder = {
                                putExtra(Extras.ProjectId, event.projectId)
                            }
                        )
                    }

                    is MainSingleEvent.NavigateToTaskDetail -> {
                        activity.startActivityWithAnimation<TaskDetailActivity>(
                            intentBuilder = {
                                putExtra(Extras.ProjectId, event.projectId)
                                putExtra(Extras.TaskId, event.taskId)
                                putExtra(Extras.IsMe, event.isMe)
                            }
                        )
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
            onClickEditIcon = { viewModel.dispatch(MainIntent.ClickEditButton(state.project.id)) },
            onClickLeftArrow = { viewModel.dispatch(MainIntent.ClickBackButton) },
            onInviteButtonClicked = { viewModel.dispatch(MainIntent.SelectAddProfile) }
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
            onClickTask = { item, isMe ->
                viewModel.dispatch(MainIntent.ClickTaskItem(state.project.id, item.id, isMe))
            }
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        FloatingActionButton(
            modifier = Modifier.padding(end = 12.dp, bottom = 12.dp),
            onClick = { viewModel.dispatch(MainIntent.ClickFab(state.project.id)) },
            backgroundColor = Purple500,
            contentColor = Color.White,
        ) {
            Icon(
                painter = painterResource(id = com.yapp.timitimi.designsystem.R.drawable.icon_plus),
                contentDescription = "create project"
            )
        }
    }
}