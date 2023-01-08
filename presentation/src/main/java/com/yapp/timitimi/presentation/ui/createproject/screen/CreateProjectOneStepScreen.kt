package com.yapp.timitimi.presentation.ui.createproject.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yapp.timitimi.component.TimiBody1Medium
import com.yapp.timitimi.component.TimiBody3Regular
import com.yapp.timitimi.component.TimiH1SemiBold
import com.yapp.timitimi.theme.Black
import com.yapp.timitimi.theme.Gray100
import com.yapp.timitimi.theme.Gray200
import com.yapp.timitimi.theme.Gray300
import com.yapp.timitimi.theme.Gray400
import com.yapp.timitimi.theme.Purple500
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.ui.createproject.redux.CreateProjectIntent
import com.yapp.timitimi.presentation.ui.createproject.redux.CreateProjectState
import com.yapp.timitimi.presentation.ui.createproject.viewmodel.CreateProjectViewModel
import com.yapp.timitimi.presentation.ui.onboarding.LargeButton


@Composable
fun CreateProjectOneStepScreen(
    viewModel: CreateProjectViewModel,
    onBackPressed: () -> Unit
) {
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    val state = viewModel.viewState.collectAsState()

    BackHandler {
        onBackPressed()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        ) {
            TimiH1SemiBold(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                text = stringResource(id = R.string.create_project_onestep_title),
            )

            CreateProjectInputField(
                viewModel = viewModel,
                focusRequester = focusRequester,
                focusManager = focusManager,
                title = stringResource(id = R.string.project_name),
                placeholder = stringResource(id = R.string.project_name_placeholder),
                strokeColor = if (state.value.hasProjectNameFieldFocused) Purple500 else Gray200,
                input = state.value.projectName,
                onFocusChanged = {
                    viewModel.dispatch(CreateProjectIntent.ChangeProjectNameTextFieldFocused(it))
                },
                onTextCleared = {
                    viewModel.dispatch(CreateProjectIntent.ClearProjectName)
                },
                onInputChange = {
                    viewModel.dispatch(CreateProjectIntent.ChangeProjectName(it))
                }
            )
            Spacing()
            CreateProjectDueDate(viewModel, state.value)
            Spacing()
            CreateProjectInputField(
                viewModel = viewModel,
                focusRequester = focusRequester,
                focusManager = focusManager,
                title = stringResource(id = R.string.project_goal),
                placeholder = "학기 성적 A+ 도전 \uD83D\uDD25",
                strokeColor = if (state.value.hasProjectGoalFocused) Purple500 else Gray200,
                input = state.value.projectGoal,
                onFocusChanged = {
                    viewModel.dispatch(CreateProjectIntent.ChangeProjectGoalTextFieldFocused(it))
                },
                onTextCleared = {
                    viewModel.dispatch(CreateProjectIntent.ClearProjectGoal)
                },
                onInputChange = {
                    viewModel.dispatch(CreateProjectIntent.ChangeProjectGoal(it))
                })
            BottomLargeButton(
                title = "다음",
                state
            ) {
                viewModel.dispatch(CreateProjectIntent.ClickNextButton)
            }
        }

    }

    if (state.value.isCalendarVisible) {
        SelectProjectDateCalendar(viewModel, state.value.openCalendarType)
    }
}

@Composable
fun BottomLargeButton(
    title: String,
    state: State<CreateProjectState>,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column {
            Divider(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
                    .height(1.dp),
                color = Gray300
            )
            Box (
                modifier = Modifier.padding(horizontal = 16.dp)
            ){
                LargeButton(
                    text = title,
                    //  backgroundColor = if (state.value.isButtonEnabled) MaterialTheme.colors.primary else Gray4,
                    //  enabled = state.value.isButtonEnabled,
                    enabled = true
                ) {
                    onClick()
                }
            }
        }
    }
}

@Composable
fun Spacing(
    height: Dp = 20.dp
) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun CreateProjectDueDate(
    viewModel: CreateProjectViewModel,
    state: CreateProjectState
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
        text = "프로젝트 기간",
    )

    Row(
        modifier = Modifier
            .height(52.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DateTextBox(
            onDueDateClick = {
                viewModel.dispatch(CreateProjectIntent.OpenDueDateCalendar(CreateProjectIntent.DueDateType.START))
            },
            text = state.projectStartDate,
            color = if (state.isNotInitializedStartDate()) Gray400 else Black

        )

        Text(
            text = "~",
            color = Black
        )
        DateTextBox(
            onDueDateClick = {
                viewModel.dispatch(CreateProjectIntent.OpenDueDateCalendar(CreateProjectIntent.DueDateType.END))
            },
            text = state.projectEndDate,
            color = if (state.isNotInitializedEndDate()) Gray400 else Black
        )
    }
}

@Composable
fun RowScope.DateTextBox(
    onDueDateClick: () -> Unit,
    text: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
            .padding(horizontal = 16.dp)
            .background(shape = RoundedCornerShape(8.dp), color = Gray100)
            .border(
                width = 1.dp,
                color = Gray200,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable { onDueDateClick() }
        ,
    ) {
        TimiBody1Medium(
            text = text,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .align(Alignment.CenterStart),
            color = color
        )
    }

}

@Composable
fun CreateProjectInputField(
    viewModel: CreateProjectViewModel,
    focusRequester: FocusRequester,
    focusManager: FocusManager,
    title: String,
    placeholder: String,
    strokeColor: Color,
    input: String,
    onFocusChanged: (Boolean) -> Unit,
    onInputChange: (String) -> Unit,
    onTextCleared: () -> Unit
) {
    TimiBody3Regular(
        modifier = Modifier
            .fillMaxWidth()
            .addFocusCleaner(focusManager)
            .padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
        text = title,
        color = Black,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterEnd,
    ) {
        TextField(
            value = input,
            onValueChange = { onInputChange(it) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester)
                .onFocusChanged {
                    onFocusChanged(it.isFocused)
                }
                .background(shape = RoundedCornerShape(8.dp), color = Gray100)
                .border(
                    width = 1.dp,
                    color = strokeColor,
                    shape = RoundedCornerShape(8.dp)
                ),
            placeholder = {
                TimiBody1Medium(
                    text = placeholder,
                    color = Gray400
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        if (input.isNotBlank()) {
            Icon(
                painter = painterResource(com.yapp.timitimi.designsystem.R.drawable.icon_input_field_delete),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
                    .clickable {
                        onTextCleared()
                    },
                contentDescription = "input field delete icon",
                tint = Color.Unspecified,
            )
        }
    }
}

fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}
