package com.yapp.timitimi.presentation.ui.intro

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yapp.timitimi.component.TimiBody2Medium
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.ui.createproject.screen.Spacing
import com.yapp.timitimi.presentation.ui.intro.kakao.KakaoLoginProvider
import com.yapp.timitimi.presentation.ui.intro.redux.IntroIntent
import com.yapp.timitimi.presentation.ui.intro.redux.IntroSingleEvent
import com.yapp.timitimi.theme.Purple200
import com.yapp.timitimi.theme.Purple500
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun IntroScreen(
    context: Context = LocalContext.current,
    viewModel: IntroViewModel = hiltViewModel()
) {
    val kakaoLoginProvider = KakaoLoginProvider(context)

    LaunchedEffect(key1 = viewModel.singleEventFlow) {
        viewModel.singleEventFlow
            .onEach { event ->
                when (event) {
                    is IntroSingleEvent.TryKakaoLogin -> {
                        val state = kakaoLoginProvider.login()
                        viewModel.login(state)
                    }
                    is IntroSingleEvent.NavigateToCreateProject -> {

                    }

                    is IntroSingleEvent.LoginFailed -> {
                        Toast.makeText(context, "카카오 로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .launchIn(this)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(listOf(White, Purple200))
            ),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.timitimi),
                modifier = Modifier
                    .padding(horizontal = 80.dp),
                contentDescription = null,
            )

            TimiBody2Medium(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.onboarding_text),
                color = Purple500,
                textAlign = TextAlign.Center
            )

            Spacing(32.dp)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Icon(
                painter = painterResource(id = R.drawable.kakako_login_button),
                modifier = Modifier
                    .wrapContentSize()
                    .timiClickable(onClick = {
                        viewModel.dispatch(IntroIntent.Login)
                    }, rippleEnabled = false)
                    .padding(16.dp),
                contentDescription = "kakao login button",
                tint = Color.Unspecified,
            )
        }
    }
}

@Preview
@Composable
fun IntroPreview() {
    IntroScreen()
}