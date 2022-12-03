package com.yapp.presentation.ui.createproject.twostep

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yapp.presentation.R
import com.yapp.presentation.ui.createproject.CreateProjectViewModel
import com.yapp.presentation.ui.createproject.onestep.BottomLargeButton
import com.yapp.presentation.ui.createproject.onestep.Spacing
import com.yapp.presentation.ui.login.LargeButton

@Composable
fun CreateProjectTwoStepScreen(
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
            text = "팀원을 초대해볼까요?"
        )
        Spacing(10.dp)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            text = "나중에 팀원을 초대해도 괜찮아요!"
        )

        LargeButton(
            text = "링크로 공유하기",
            textColor = Color.Black,
            backgroundColor = Color.White,
            strokeColor = Color.Magenta,
            enabled = true
        ) {

        }

        BottomLargeButton(state, navigate)
    }

}