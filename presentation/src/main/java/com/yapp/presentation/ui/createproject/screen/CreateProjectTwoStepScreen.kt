package com.yapp.presentation.ui.createproject.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yapp.designsystem.theme.*
import com.yapp.presentation.ui.createproject.redux.CreateProjectIntent
import com.yapp.presentation.ui.createproject.viewmodel.CreateProjectViewModel
import com.yapp.presentation.ui.createproject.screen.BottomLargeButton
import com.yapp.presentation.ui.createproject.screen.Spacing

@Composable
fun CreateProjectTwoStepScreen(
    viewModel: CreateProjectViewModel
) {
    val state = viewModel.viewState.collectAsState()

    BackHandler {
        viewModel.dispatch(CreateProjectIntent.ClickBackButton)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            text = "팀원을 초대해볼까요?",
            color = Black
        )
        Spacing(10.dp)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            text = "나중에 팀원을 초대해도 괜찮아요!",
            //color = Gray2
        )

        ShareKakaoUrlButton()
        BottomLargeButton(
            title = "프로젝트 시작",
            state
        ) {
         //   viewModel.dispatch(CreateProjectIntent.StartMain)
        }
    }

}

@Composable
fun ShareKakaoUrlButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(shape = RoundedCornerShape(16.dp), color = Color.White)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                painter = painterResource(com.yapp.designsystem.R.drawable.icon_share),
                modifier = Modifier
                    .wrapContentSize(),
                contentDescription = "kakao url share icon",
                tint = MaterialTheme.colors.primary,
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .wrapContentSize(),
                text = "링크로 공유하기",
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center
            )
        }
    }
}