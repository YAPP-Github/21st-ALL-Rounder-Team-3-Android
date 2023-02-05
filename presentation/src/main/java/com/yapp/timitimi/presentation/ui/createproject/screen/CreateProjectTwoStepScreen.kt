package com.yapp.timitimi.presentation.ui.createproject.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yapp.timitimi.component.TimiBody2Medium
import com.yapp.timitimi.component.TimiH1SemiBold
import com.yapp.timitimi.component.TimiH3SemiBold
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.ui.createproject.redux.CreateProjectIntent
import com.yapp.timitimi.presentation.ui.createproject.viewmodel.CreateProjectViewModel
import com.yapp.timitimi.theme.Black
import com.yapp.timitimi.theme.Gray600
import com.yapp.timitimi.theme.Purple500

@Composable
fun CreateProjectTwoStepScreen(
    viewModel: CreateProjectViewModel,
    onBackPressed: () -> Unit
) {
    val state = viewModel.viewState.collectAsState()

    BackHandler {
        onBackPressed()
    }

    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {
        TimiH1SemiBold(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = stringResource(id = R.string.create_project_twostep_title),
            color = Black
        )
        Spacing(10.dp)
        TimiBody2Medium(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = stringResource(id = R.string.create_project_twostep_description),
            color = Gray600
        )
        Spacing(40.dp)
        ShareKakaoUrlButton {
            viewModel.dispatch(CreateProjectIntent.ShareProjectDeeplink)
        }
        BottomLargeButton(
            title = stringResource(id = R.string.project_start),
        ) {
            viewModel.dispatch(CreateProjectIntent.StartMain)
        }
    }

}

@Composable
fun ShareKakaoUrlButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
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
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                painter = painterResource(com.yapp.timitimi.designsystem.R.drawable.icon_share),
                modifier = Modifier
                    .wrapContentSize(),
                contentDescription = "kakao url share icon",
                tint = Purple500,
            )
            TimiH3SemiBold(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .wrapContentSize(),
                text = stringResource(id = R.string.create_project_twostep_share_button_title),
                color = Purple500,
                textAlign = TextAlign.Center
            )
        }
    }
}