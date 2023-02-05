@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.yapp.timitimi.presentation.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.germainkevin.collapsingtopbar.CollapsingTopBarScrollBehavior
import com.germainkevin.collapsingtopbar.rememberCollapsingTopBarScrollBehavior
import com.yapp.timitimi.border.TimiBorder
import com.yapp.timitimi.component.TimiBody2Medium
import com.yapp.timitimi.component.TimiBody3Regular
import com.yapp.timitimi.component.TimiCaption1Regular
import com.yapp.timitimi.component.TimiCaption2Regular
import com.yapp.timitimi.component.TimiH2SemiBold
import com.yapp.timitimi.component.TimiH3SemiBold
import com.yapp.timitimi.component.TimiMediumRoundedBadge
import com.yapp.timitimi.component.TopBarNotificationIcon
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.modifier.timiClipBorder
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.ui.main.components.TaskContent
import com.yapp.timitimi.presentation.ui.main.redux.MainIntent
import com.yapp.timitimi.presentation.ui.main.redux.MainSingleEvent
import com.yapp.timitimi.theme.Gray200
import com.yapp.timitimi.theme.Gray700
import com.yapp.timitimi.theme.Purple500
import kotlinx.collections.immutable.ImmutableList
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
            members = dummyMembers.toImmutableList(),
            onProfileSelected = { viewModel.dispatch(MainIntent.OnSelectProfile(it)) },
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            TaskContent(
                title = "나의 할일"
            )
        }
    }
}

@Composable
fun Header(
    scrollBehavior: CollapsingTopBarScrollBehavior,
    title: String,
    memo: String,
    startDate: String,
    endDate: String,
    notificationCount: Int,
    selectedProfileIndex: Int,
    members: ImmutableList<Member>,
    onProfileSelected: (index: Int) -> Unit,
) {
    Card(
        modifier = Modifier.padding(bottom = 8.dp),
        elevation = 8.dp,
    ) {
        Column(
            Modifier.padding(horizontal = 16.dp),
        ) {
            MainTopAppBar(
                onClickLeftArrow = {},
                onClickNotification = {},
                leftContent = {
                    TimiH3SemiBold(
                        text = title,
                        color = Color.Black
                    )
                },
                collapsed = scrollBehavior.isCollapsed,
                notificationCount = notificationCount,
            )
            AnimatedVisibility(
                visible = scrollBehavior.isExpanded,
            ) {
                Column {
                    TimiH2SemiBold(
                        text = title,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    BadgeString( //TODO(EvergreenTree97) 디데이와 날짜 계산하도록 변경
                        title = "$startDate ~ $endDate",
                        badgeText = "D-10",
                        space = 8.dp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TimiBody3Regular(text = memo)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            MemberContents(
                title = "팀원 7명",
                members = members,
                selectedProfileIndex = selectedProfileIndex,
                onProfileSelected = onProfileSelected,
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@Composable
fun MainTopAppBar(
    onClickLeftArrow: () -> Unit,
    onClickNotification: () -> Unit,
    notificationCount: Int,
    leftContent: @Composable () -> Unit = {},
    collapsed: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 48.dp)
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.clickable(
                    onClick = onClickLeftArrow
                ),
                painter = painterResource(id = com.yapp.timitimi.designsystem.R.drawable.icon_arrow_left),
                contentDescription = "leftArrow"
            )
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = if (collapsed) {
                    Arrangement.Start
                } else {
                    Arrangement.Center
                }
            ) {
                if (collapsed) {
                    leftContent()
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo"
                    )
                }
            }
            TopBarNotificationIcon(
                count = notificationCount,
                onClick = onClickNotification,
            )
        }
    }
}

data class Member(
    val profile: String,
    val name: String,
    val selected: Boolean
)

val dummyMembers = listOf(
    Member("https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg", "상록", true),
    Member("https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg", "세희", false),
    Member("https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg", "지율", true)
)

@Composable
fun MemberContents(
    title: String,
    selectedProfileIndex: Int,
    members: ImmutableList<Member>,
    onProfileSelected: (index: Int) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 6.dp)
    ) {
        TimiCaption1Regular(text = title)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            item {
                MemberProfile(
                    profile = painterResource(id = R.drawable.total_profile),
                    name = stringResource(id = R.string.main_total),
                    selected = selectedProfileIndex == 0,
                    onClick = { onProfileSelected(0) }
                )
            }
            itemsIndexed(
                items = members,
                key = { _, item -> item.name },
            ) { index, member ->
                val profileIndex = index + 1
                MemberProfile(
                    profile = member.profile,
                    name = member.name,
                    selected = selectedProfileIndex == profileIndex,
                    onClick = { onProfileSelected(profileIndex) }
                )
            }
        }
    }
}

@Composable
fun MemberProfile(
    profile: Any?,
    name: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val profileBorderColor by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colors.primary
        } else {
            Color.White
        }
    )
    val fontColor by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colors.primary
        } else {
            Color.Black
        }
    )
    val border = if (selected) {
        TimiBorder(
            width = 2.dp,
            color = profileBorderColor
        )
    } else {
        null
    }

    Column(
        modifier = Modifier.timiClickable(
            onClick = onClick,
            rippleEnabled = false,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (profile is Painter) {
            Image(
                modifier = Modifier
                    .size(size = 40.dp)
                    .timiClipBorder(
                        border = border,
                        shape = CircleShape,
                    ),
                painter = profile,
                contentDescription = "profile"
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .size(size = 40.dp)
                    .timiClipBorder(
                        border = border,
                        shape = CircleShape,
                    ),
                model = profile,
                contentScale = ContentScale.Crop,
                contentDescription = "profile"
            )
        }
        TimiCaption2Regular(
            text = name,
            color = fontColor
        )
    }
}


@Composable
fun BadgeString(
    title: String,
    badgeText: String,
    space: Dp = 0.dp,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = space)
    ) {
        TimiMediumRoundedBadge(
            text = badgeText,
            border = null,
            backgroundColor = Purple500,
            fontColor = Color.White
        )
        TimiBody2Medium(
            text = title,
            color = Gray700
        )
    }
}
