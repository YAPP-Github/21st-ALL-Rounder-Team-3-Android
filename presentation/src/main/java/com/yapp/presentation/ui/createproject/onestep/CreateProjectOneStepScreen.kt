package com.yapp.presentation.ui.createproject.onestep

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yapp.presentation.R
import com.yapp.presentation.ui.createproject.CreateProjectIntent
import com.yapp.presentation.ui.createproject.CreateProjectState
import com.yapp.presentation.ui.createproject.CreateProjectViewModel
import com.yapp.presentation.ui.login.LargeButton


@Composable
fun CreateProjectOneStepScreen(
    viewModel: CreateProjectViewModel,
    navigate: () -> Unit,
) {
    val state = viewModel.viewState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            text = stringResource(id = R.string.create_project_onestep_title)
        )

        CreateProjectInputField(
            title = stringResource(id = R.string.project_name),
            placeholder = "프로젝트 이름을 입력하세요.",
            state.value.projectName
        ) {
            viewModel.dispatch(CreateProjectIntent.ChangeProjectName(it))
        }
        Spacing()
        CreateProjectDueDate(state.value)
        Spacing()
        CreateProjectInputField(
            title = stringResource(id = R.string.project_goal),
            placeholder = "학기 성적 A+ 도전 :fire:",
            state.value.projectGoal
        ) {
            viewModel.dispatch(CreateProjectIntent.ChangeProjectGoal(it))
        }
        Spacing()
        CreateProjectDropDown(
            state.value.isDropDownVisible
        ) {
            viewModel.dispatch(CreateProjectIntent.OnClickDropDown)
        }

        BottomLargeButton(state, navigate)
    }

}

@Composable
fun BottomLargeButton(
    state: State<CreateProjectState>,
    navigate: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        LargeButton(
            text = "다음",
            backgroundColor = if (state.value.isButtonEnabled) Color.Gray
            else Color.Magenta,
            enabled = state.value.isButtonEnabled,
        ) {
            navigate()
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
fun CreateProjectDueDate(state: CreateProjectState) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        text = "프로젝트 기간",
    )

    Row(
        modifier = Modifier.height(40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DateTextBox(text = state.projectStartDate)

        Text(
            modifier = Modifier
                .padding(10.dp),
            text = "~"
        )

        DateTextBox(text = state.projectEndDate)
    }
}

@Composable
fun RowScope.DateTextBox(
    text: String,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
            .background(shape = RoundedCornerShape(8.dp), color = Color.Gray),
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .align(Alignment.CenterStart),
        )
    }

}

@Composable
fun CreateProjectInputField(
    title: String,
    placeholder: String,
    input: String,
    onInputChange: (String) -> Unit
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        text = title,
        color = Color.Black
    )

    TextField(
        value = input,
        onValueChange = { onInputChange(it) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(8.dp), color = Color.Gray),
        placeholder = {
            Text(
                text = placeholder,
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
}

@Composable
fun CreateProjectDropDown(
    isChildListVisible: Boolean,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        text = "프로젝트 난이도",
        color = Color.Black
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(shape = RoundedCornerShape(8.dp), color = Color.Gray)
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "난이도를 선택하세요.",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                tint = Color.Unspecified,
                contentDescription = null,
            )
        }
    }

    if (isChildListVisible) {
        Spacing(4.dp)
        ChildDropDownList()
    }
}

@Composable
fun ChildDropDownList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(shape = RoundedCornerShape(8.dp), color = Color.Gray),
    ) {
        ChildDropDownText("여유있게 우리의 목표는 완주!") {

        }
        ChildDropDownText("적당하게 B 이상 가보자고") {

        }
        ChildDropDownText("완벽하게 A+을 위하여") {

        }
    }
}

@Composable
fun ChildDropDownText(
    title: String,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterStart),
            text = title
        )
    }
}

@Preview
@Composable
fun CreateProjectOneStepScreenPreview() {
}