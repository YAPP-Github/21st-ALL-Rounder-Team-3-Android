package com.yapp.timitimi.presentation.ui.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.yapp.timitimi.border.TimiBorder
import com.yapp.timitimi.component.TimiBody3Regular
import com.yapp.timitimi.component.TimiCaption1Regular
import com.yapp.timitimi.component.TimiSmallRoundedBadge
import com.yapp.timitimi.component.TimiTopAppBar
import com.yapp.timitimi.component.TimiTwoButtonDialog
import com.yapp.timitimi.domain.entity.EditProjectInfo
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.modifier.timiClipBorder
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.ui.createproject.screen.BottomLargeButton
import com.yapp.timitimi.presentation.ui.createproject.screen.CalenderDueDateType
import com.yapp.timitimi.presentation.ui.createproject.screen.CreateProjectDueDate
import com.yapp.timitimi.presentation.ui.createproject.screen.SelectProjectDateCalendar
import com.yapp.timitimi.presentation.ui.createproject.screen.Spacing
import com.yapp.timitimi.presentation.ui.createproject.screen.TimiInputField
import com.yapp.timitimi.presentation.ui.createproject.screen.addFocusCleaner
import com.yapp.timitimi.presentation.ui.edit.redux.EditProjectIntent
import com.yapp.timitimi.presentation.ui.edit.redux.EditProjectSingleEvent
import com.yapp.timitimi.theme.Black
import com.yapp.timitimi.theme.Gray200
import com.yapp.timitimi.theme.Gray300
import com.yapp.timitimi.theme.Gray500
import com.yapp.timitimi.theme.Gray700
import com.yapp.timitimi.theme.Purple100
import com.yapp.timitimi.theme.Purple200
import com.yapp.timitimi.theme.Purple500
import com.yapp.timitimi.ui.finishWithAnimation
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun EditProjectScreen(
    viewModel: EditProjectViewModel = hiltViewModel(),
) {
    val activity = LocalContext.current as EditProjectActivity
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current
    val lazyListState = rememberLazyListState()

    val state = viewModel.viewState.collectAsState()

    LaunchedEffect(viewModel.singleEventFlow) {
        viewModel.singleEventFlow
            .onEach { event ->
                when (event) {
                    EditProjectSingleEvent.Exit -> {
                        activity.finishWithAnimation()
                    }
                }
            }
            .launchIn(this)
    }

    var leaderDialog by remember {
        mutableStateOf(false)
    }

    var removeDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TimiTopAppBar(
                isTextCenterAlignment = true,
                onClickBackButton = { activity.finish() },
                title = "수정하기",
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
            ) {
                TimiInputField(
                    focusRequester = focusRequester,
                    focusManager = focusManager,
                    title = stringResource(id = R.string.project_name),
                    placeholder = stringResource(id = R.string.project_name_placeholder),
                    strokeColor = if (state.value.hasProjectNameFieldFocused) Purple500 else Gray200,
                    input = state.value.projectName,
                    onFocusChanged = {
                        viewModel.dispatch(EditProjectIntent.ChangeProjectNameTextFieldFocused(it))
                    },
                    onTextCleared = {
                        viewModel.dispatch(EditProjectIntent.ClearProjectName)
                    },
                    onInputChange = {
                        viewModel.dispatch(EditProjectIntent.ChangeProjectName(it))
                    }
                )
                Spacing()
                CreateProjectDueDate(
                    onStartDueDateClicked = {
                        viewModel.dispatch(
                            EditProjectIntent.OpenDueDateCalendar(
                                CalenderDueDateType.START
                            )
                        )
                    },
                    onEndDueDateClicked = {
                        viewModel.dispatch(
                            EditProjectIntent.OpenDueDateCalendar(
                                CalenderDueDateType.END
                            )
                        )
                    },
                    startDate = state.value.projectStartDate,
                    endDate = state.value.projectEndDate,
                    isStartDateInitialized = state.value.isNotInitializedStartDate(),
                    isEndDateInitialized = state.value.isNotInitializedEndDate()
                )
                Spacing()
                TimiInputField(
                    focusRequester = focusRequester,
                    focusManager = focusManager,
                    title = stringResource(id = R.string.project_goal),
                    placeholder = "학기 성적 A+ 도전 \uD83D\uDD25",
                    strokeColor = if (state.value.hasProjectGoalFocused) Purple500 else Gray200,
                    input = state.value.projectGoal,
                    onFocusChanged = {
                        viewModel.dispatch(EditProjectIntent.ChangeProjectGoalTextFieldFocused(it))
                    },
                    onTextCleared = {
                        viewModel.dispatch(EditProjectIntent.ClearProjectGoal)
                    },
                    onInputChange = {
                        viewModel.dispatch(EditProjectIntent.ChangeProjectGoal(it))
                    })

                Spacing()
                TimiBody3Regular(
                    modifier = Modifier
                        .fillMaxWidth()
                        .addFocusCleaner(focusManager)
                        .padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
                    text = "팀원 (${state.value.participantList.size}명)",
                    color = Black,
                )

                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier.padding(top = 12.dp, bottom = 60.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(
                        items = state.value.participantList,
                        key = { it.id }
                    ) { item ->
                        TeamMemberItem(
                            profileUrl = item.profileUrl,
                            onRemoveButtonClicked = {
                                removeDialog = true
                            },
                            onEmpowermentButtonClicked = {
                                leaderDialog = true
                            },
                            nickName = {
                                if (item.id == state.value.myId) {
                                    "나"
                                } else {
                                    item.nickName
                                }
                            },
                            isLeader = item.isLeader,
                        )
                    }
                }
            }

            BottomLargeButton(
                backgroundColor = if (state.value.isButtonEnabled) Purple500 else Purple200,
                isEnabled = state.value.isButtonEnabled,
                onClick = {
                    viewModel.dispatch(
                        state.value.run {
                            EditProjectIntent.CompleteEdit(
                                projectId = projectId,
                                projectInfo = EditProjectInfo(
                                    name = projectName,
                                    startDate = projectStartDate,
                                    dueDate = projectEndDate,
                                    goal = projectGoal,
                                )
                            )
                        }
                    )
                },
                title = "완료",
            )
        }


        if (state.value.isCalendarVisible) {
            SelectProjectDateCalendar(
                onStartDueDateFilled = {
                    viewModel.dispatch(
                        EditProjectIntent.SelectStartProjectDate(
                            date = it
                        )
                    )
                },
                onEndDueDateFilled = {
                    viewModel.dispatch(
                        EditProjectIntent.SelectEndProjectDate(
                            date = it
                        )
                    )
                },
                onDismissed = {
                    viewModel.dispatch(EditProjectIntent.CloseCalendar)
                },
                state.value.openCalendarType
            )
        }

        if (leaderDialog) {
            TimiTwoButtonDialog(
                titlePairs = listOf(
                    Pair("정말 ", Black),
                    Pair("가연", Purple500),
                    Pair("님께\n팀장 권한을 넘기시겠어요?", Black)
                ),
                description = "더 이상 프로젝트 관리를 할 수 없어요.",
                positiveText = "팀장 넘기기",
                negativeText = "취소하기",
                onNegativeButtonClicked = { leaderDialog = false },
                onPositiveButtonClicked = { leaderDialog = false },
                onDismissRequest = { leaderDialog = false }
            )
        }

        if (removeDialog) {
            TimiTwoButtonDialog(
                titlePairs = listOf(
                    Pair("정말 ", Black),
                    Pair("가연", Purple500),
                    Pair("님을 팀원 삭제하시겠어요?", Black)
                ),
                description = "여태까지 가연님이 수행한 업무도\n모두 삭제돼요.",
                positiveText = "삭제하기",
                negativeText = "취소하기",
                onNegativeButtonClicked = { removeDialog = false },
                onPositiveButtonClicked = { removeDialog = false },
                onDismissRequest = { removeDialog = false }
            )
        }
    }
}

@Composable
fun TeamMemberItem(
    isLeader: Boolean = true,
    nickName: () -> String,
    profileUrl: String,
    onRemoveButtonClicked: () -> Unit,
    onEmpowermentButtonClicked: () -> Unit,
) {
    val name = nickName().let {
        if (isLeader) {
            "$it(팀장)"
        } else {
            it
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(size = 40.dp)
                    .timiClipBorder(
                        shape = CircleShape,
                    ),
                model = profileUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            TimiCaption1Regular(
                text = name,
                color = Gray700
            )
        }
        if (isLeader.not()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentSize()
            ) {
                TimiSmallRoundedBadge(
                    text = "팀장 넘기기",
                    border = TimiBorder(
                        color = Purple100,
                    ),
                    backgroundColor = Purple100.copy(
                        alpha = 0.6f
                    ),
                    fontColor = Purple500,
                    modifier = Modifier
                        .timiClickable(onEmpowermentButtonClicked, rippleEnabled = true)
                )

                Spacer(modifier = Modifier.padding(start = 12.dp))

                TimiSmallRoundedBadge(
                    text = "삭제",
                    border = TimiBorder(
                        color = Purple100,
                    ),
                    backgroundColor = Gray300.copy(
                        alpha = 0.6f
                    ),
                    fontColor = Gray500,
                    modifier = Modifier
                        .timiClickable(onRemoveButtonClicked, rippleEnabled = true)
                )
            }
        }
    }
}