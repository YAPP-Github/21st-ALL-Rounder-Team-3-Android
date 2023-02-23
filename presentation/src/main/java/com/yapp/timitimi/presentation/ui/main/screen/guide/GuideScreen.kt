package com.yapp.timitimi.presentation.ui.main.screen.guide

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.germainkevin.collapsingtopbar.rememberCollapsingTopBarScrollBehavior
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yapp.timitimi.component.TaskType
import com.yapp.timitimi.designsystem.R
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.presentation.ui.main.redux.Member
import com.yapp.timitimi.presentation.ui.main.redux.ScreenStep
import com.yapp.timitimi.presentation.ui.main.redux.TaskItem
import com.yapp.timitimi.presentation.ui.main.screen.BottomNavigationItem
import com.yapp.timitimi.presentation.ui.main.screen.components.Header
import com.yapp.timitimi.presentation.ui.main.screen.components.RoundedAddButton
import com.yapp.timitimi.presentation.ui.main.screen.components.TaskClassification
import com.yapp.timitimi.presentation.ui.main.screen.components.taskDropBox
import com.yapp.timitimi.theme.Gray200
import com.yapp.timitimi.theme.Purple500
import kotlinx.collections.immutable.persistentListOf

@Composable
fun GuideScreen(
    onClose: () -> Unit,
    currentStep: ScreenStep
) {
    val items = listOf(
        BottomNavigationItem.HOME,
        BottomNavigationItem.MY_PAGE,
    )

    val uiController = rememberSystemUiController()
    var originOffset by remember { mutableStateOf(Offset.Zero) }
    var buttonSize by remember { mutableStateOf(IntSize.Zero) }
    var tooltipSize by remember { mutableStateOf(IntSize.Zero) }

    SideEffect {
        uiController.setStatusBarColor(
            color = Color.Black.copy(alpha = 0.5f)
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    backgroundColor = Color.White
                ) {
                    items.forEachIndexed { index, screen ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painterResource(screen.icon),
                                    contentDescription = null
                                )
                            },
                            selected = index == 0,
                            onClick = {}
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = rememberNavController(),
                startDestination = BottomNavigationItem.HOME.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(BottomNavigationItem.HOME.route) {
                    GuideBackground(
                        modifier = Modifier.padding(innerPadding),
                        addMemberOffset = {
                            if (currentStep == ScreenStep.First) {
                                originOffset = it
                            }
                        },
                        fabOffset = {
                            if (currentStep == ScreenStep.Second) {
                                originOffset = it
                            }
                        }
                    )
                }
            }
        }
        Dimmed(onClose = onClose)
        if (currentStep == ScreenStep.First) {
            if (originOffset.x != 0f && originOffset.y != 0f) {
                Column(
                    modifier = Modifier.offset {
                        IntOffset(
                            x = originOffset.x.toInt() - tooltipSize.width + buttonSize.width,
                            y = originOffset.y.toInt()
                        )
                    },
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    RoundedAddButton(
                        modifier = Modifier.onSizeChanged { buttonSize = it },
                        onClick = { }
                    )
                    Image(
                        modifier = Modifier
                            .onSizeChanged { tooltipSize = it }
                            .offset { IntOffset(x = 16, y = 0) },
                        painter = painterResource(id = com.yapp.timitimi.presentation.R.drawable.tooltip_invite_member),
                        contentDescription = null,
                    )
                }
            }
        } else if (currentStep == ScreenStep.Second) {
            if (originOffset.x != 0f && originOffset.y != 0f) {
                Column(
                    modifier = Modifier.offset {
                        IntOffset(
                            x = originOffset.x.toInt() - tooltipSize.width + buttonSize.width,
                            y = originOffset.y.toInt() - buttonSize.height - 8.dp.toPx().toInt()
                        )
                    },
                    horizontalAlignment = Alignment.End,
                ) {
                    Image(
                        modifier = Modifier
                            .onSizeChanged { tooltipSize = it },
                        painter = painterResource(id = com.yapp.timitimi.presentation.R.drawable.tooltip_add_task),
                        contentDescription = null,
                    )
                    FloatingActionButton(
                        modifier = Modifier.onSizeChanged { buttonSize = it },
                        onClick = { },
                        backgroundColor = Purple500,
                        contentColor = Color.White,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_plus),
                            contentDescription = "create project"
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun guideMembers() = persistentListOf(
    Member(
        id = 0,
        isLeader = false,
        profile = painterResource(id = com.yapp.timitimi.presentation.R.drawable.guide_profile1),
        name = "김티미"
    ),
    Member(
        id = 1,
        isLeader = true,
        profile = painterResource(id = com.yapp.timitimi.presentation.R.drawable.guide_profile2),
        name = "박팀장"
    ),
    Member(
        id = 2,
        isLeader = false,
        profile = painterResource(id = com.yapp.timitimi.presentation.R.drawable.guide_profile3),
        name = "최티미"
    )
)

@Composable
private fun GuideBackground(
    modifier: Modifier = Modifier,
    addMemberOffset: ((Offset) -> Unit)? = null,
    fabOffset: ((Offset) -> Unit)? = null,
) {
    val scrollBehavior = rememberCollapsingTopBarScrollBehavior()
    val secondGuideProfile =
        painterResource(id = com.yapp.timitimi.presentation.R.drawable.guide_profile1)
    Column(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(color = Gray200)
    ) {
        Header(
            scrollBehavior = scrollBehavior,
            title = "평화로운 팀플의 이해 1조",
            memo = "무조건 A+! 가보자고!",
            startDate = "10.23",
            endDate = "11.12",
            dDay = "D-9",
            notificationCount = 1,
            selectedProfileIndex = 0,
            members = guideMembers(),
            onProfileSelected = { },
            onClickEditIcon = { },
            addMemberOffset = addMemberOffset,
            onClickLeftArrow = { },
            onInviteButtonClicked = { }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Gray200)
                .offset {
                    IntOffset(x = 0, y = 8)
                }
                .padding(horizontal = 16.dp)
                .padding(top = 22.dp),
        ) {
            TaskClassification.Whole.values().forEachIndexed { index, task ->
                taskDropBox(
                    title = task.text,
                    tasks = when (task) {
                        TaskClassification.Whole.My -> persistentListOf(
                            TaskItem(
                                taskType = TaskType.InProgress,
                                id = 0,
                                startDate = "11.01",
                                endDate = "11.09",
                                title = "팀플학개론 1챕터",
                                memo = "요약, 분석해서 워드로 보내주기"
                            )
                        )

                        TaskClassification.Whole.Other -> persistentListOf(
                            TaskItem(
                                taskType = TaskType.FeedBack(
                                    currentConfirmation = 1,
                                    totalConfirmation = 2,
                                ),
                                id = 1,
                                member = Member(
                                    id = 1,
                                    isLeader = true,
                                    profile = secondGuideProfile,
                                    name = "박팀장"
                                ),
                                startDate = "10.25",
                                endDate = "11.03",
                                title = "팀플의 역사 논문 리서치",
                                memo = "출처 표기 지켜주기"
                            )
                        )

                        TaskClassification.Whole.Done -> persistentListOf(
                            TaskItem(
                                taskType = TaskType.Done,
                                id = 2,
                                startDate = "10.24",
                                endDate = "11.01",
                                title = "C프로그래밍",
                                memo = "꼼꼼하게"
                            )
                        )
                    },
                    isHide = false,
                    onClick = {},
                    isMe = false,
                    onClickTask = { _, _ -> },
                )
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        FloatingActionButton(
            modifier = Modifier
                .padding(end = 12.dp, bottom = 12.dp)
                .onGloballyPositioned {
                    fabOffset?.invoke(it.positionInRoot())
                },
            onClick = { },
            backgroundColor = Purple500,
            contentColor = Color.White,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_plus),
                contentDescription = "create project"
            )
        }
    }
}

@Composable
private fun Dimmed(
    onClose: () -> Unit,
    alpha: Float = 0.5f,
    color: Color = Color.Black,
) {
    Canvas(
        Modifier
            .fillMaxSize()
            .timiClickable(
                onClick = onClose,
                rippleEnabled = false
            )
    ) {
        drawRect(color, alpha = alpha)
    }
}
