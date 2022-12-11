package com.yapp.presentation.ui.createproject.onestep

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yapp.presentation.R
import com.yapp.presentation.theme.Black
import com.yapp.presentation.theme.Gray2
import com.yapp.presentation.theme.Gray4
import com.yapp.presentation.theme.Gray6
import com.yapp.presentation.theme.Gray7
import com.yapp.presentation.ui.createproject.base.CreateProjectIntent
import com.yapp.presentation.ui.createproject.base.CreateProjectState
import com.yapp.presentation.ui.createproject.CreateProjectViewModel
import com.yapp.presentation.ui.login.LargeButton
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date


@Composable
fun CreateProjectOneStepScreen(
    viewModel: CreateProjectViewModel,
) {
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current
    val state = viewModel.viewState.collectAsState()

    BackHandler {
        viewModel.dispatch(CreateProjectIntent.ClickBackButton)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager) {
                viewModel.dispatch(CreateProjectIntent.CloseDropDownList)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                style = MaterialTheme.typography.h1,
                text = stringResource(id = R.string.create_project_onestep_title),
            )

            CreateProjectInputField(
                viewModel = viewModel,
                focusRequester = focusRequester,
                title = stringResource(id = R.string.create_project_name_title),
                placeholder = stringResource(id = R.string.create_project_name_placeholder),
                strokeColor = if (state.value.hasProjectNameFieldFocused) MaterialTheme.colors.primary else Gray6,
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
                title = stringResource(id = R.string.create_project_goal_title),
                placeholder = stringResource(id = R.string.create_project_goal_placeholder),
                strokeColor = if (state.value.hasProjectGoalFocused) MaterialTheme.colors.primary else Gray6,
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
            Spacing()

            CreateProjectDropDown(
                viewModel,
                state.value
            ) {
                focusManager.clearFocus()
                viewModel.dispatch(CreateProjectIntent.OnClickDropDown)
            }

            BottomLargeButton(
                title = stringResource(id = R.string.next),
                state
            ) {
                viewModel.dispatch(CreateProjectIntent.ClickNextButton)
            }
        }

    }

    if (state.value.isCalendarVisible) {
        DatePickerDialog(
            viewModel,
            state.value.openCalendarType,
            SimpleDateFormat("yyyy-MM-dd").format(Date())
        )
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
            .padding(bottom = 10.dp),
        text = stringResource(id = R.string.create_project_due_date_title),
    )

    Row(
        modifier = Modifier.height(52.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DateTextBox(
            onDueDateClick = {
                viewModel.dispatch(CreateProjectIntent.OpenDueDateCalendar(CreateProjectIntent.DueDateType.START))
            },
            text = state.projectStartDate
                ?: stringResource(id = R.string.create_project_due_date_start_placeholder),
            color = if (state.isNotInitializedStartDate()) Gray4 else Black
        )

        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = "~",
            color = Black
        )
        DateTextBox(
            onDueDateClick = { viewModel.clickProjectEndDate() },
            text = state.projectEndDate
                ?: stringResource(id = R.string.create_project_due_date_end_placeholder),
            color = if (state.isNotInitializedEndDate()) Gray4 else Black
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
            .clip(RoundedCornerShape(8.dp))
            .background(shape = RoundedCornerShape(8.dp), color = Gray7)
            .border(
                width = 1.dp,
                color = Gray6,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onDueDateClick() },
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .align(Alignment.CenterStart),
            style = MaterialTheme.typography.body1,
            color = color
        )
    }

}

@Composable
fun CreateProjectInputField(
    viewModel: CreateProjectViewModel,
    focusRequester: FocusRequester,
    title: String,
    placeholder: String,
    strokeColor: Color,
    input: String,
    onFocusChanged: (Boolean) -> Unit,
    onInputChange: (String) -> Unit,
    onTextCleared: () -> Unit
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        text = title,
        color = Gray2,
        style = MaterialTheme.typography.body2,
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
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
                .background(shape = RoundedCornerShape(8.dp), color = Gray7)
                .border(
                    width = 1.dp,
                    color = strokeColor,
                    shape = RoundedCornerShape(8.dp)
                ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.body1,
                    color = Gray4
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        if (input.isNotBlank()) {
            Icon(
                painter = painterResource(R.drawable.icon_input_field_delete),
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

@Composable
fun CreateProjectDropDown(
    viewModel: CreateProjectViewModel,
    state: CreateProjectState,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        text = stringResource(id = R.string.create_project_difficulty_title),
        style = MaterialTheme.typography.body2,
        color = Color.Black
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(shape = RoundedCornerShape(8.dp), color = Gray7)
            .border(
                width = 1.dp,
                color = Gray6,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = state.projectDifficulty.titleRes),
                style = MaterialTheme.typography.body1,
                color = if (state.projectDifficulty == ProjectDifficulty.NONE) Gray4 else Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                tint = if (state.projectDifficulty == ProjectDifficulty.NONE) Gray4 else Black,
                contentDescription = null,
            )
        }
    }

    Spacing(4.dp)

    Box(modifier = Modifier.fillMaxWidth()) {
        if (state.projectDifficulty.description != null) {
            Text(
                text = stringResource(id = state.projectDifficulty.description),
                style = MaterialTheme.typography.body1,
                color = Gray2,
                modifier = Modifier.fillMaxWidth()
            )
        }

        AnimatedVisibility(visible = state.isDropDownVisible) {
            ChildDropDownList(viewModel)
        }
    }
}

@Composable
fun ChildDropDownList(viewModel: CreateProjectViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                shape = RoundedCornerShape(8.dp),
                color = Gray7
            )
            .padding(4.dp),
    ) {
        ChildDropDownText(ProjectDifficulty.EASY) {
            viewModel.dispatch(
                CreateProjectIntent.OnClickDropDownItem(
                    ProjectDifficulty.EASY
                )
            )
        }
        ChildDropDownText(ProjectDifficulty.MEDIUM) {
            viewModel.dispatch(
                CreateProjectIntent.OnClickDropDownItem(
                    ProjectDifficulty.MEDIUM
                )
            )

        }
        ChildDropDownText(ProjectDifficulty.HARD) {
            viewModel.dispatch(
                CreateProjectIntent.OnClickDropDownItem(
                    ProjectDifficulty.HARD
                )
            )
        }
    }
}

enum class ProjectDifficulty(
    @StringRes val titleRes: Int,
    @StringRes val description: Int?
) {
    NONE(R.string.create_project_difficulty_placeholder, null),
    EASY(
        R.string.create_project_difficulty_easy,
        R.string.create_project_difficulty_easy_description
    ),
    MEDIUM(
        R.string.create_project_difficulty_medium,
        R.string.create_project_difficulty_medium_description
    ),
    HARD(
        R.string.create_project_difficulty_hard,
        R.string.create_project_difficulty_hard_description
    )
}

@Composable
fun ChildDropDownText(
    difficulty: ProjectDifficulty,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterStart),
            style = MaterialTheme.typography.body1,
            text = stringResource(id = difficulty.titleRes),
            color = Black
        )
    }
}

@Preview
@Composable
fun CreateProjectOneStepScreenPreview() {
}