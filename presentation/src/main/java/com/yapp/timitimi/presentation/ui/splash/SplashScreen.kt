package com.yapp.timitimi.presentation.ui.splash

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yapp.timitimi.component.TimiBody2Medium
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.helper.FirebaseDynamicLinkHelper
import com.yapp.timitimi.presentation.ui.createproject.CreateProjectActivity
import com.yapp.timitimi.presentation.ui.createproject.screen.Spacing
import com.yapp.timitimi.presentation.ui.intro.IntroActivity
import com.yapp.timitimi.presentation.ui.main.MainActivity
import com.yapp.timitimi.presentation.ui.splash.redux.SplashSingleEvent
import com.yapp.timitimi.theme.Purple200
import com.yapp.timitimi.theme.Purple500
import com.yapp.timitimi.ui.startActivityWithAnimation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    firebaseDynamicLinkHelper: FirebaseDynamicLinkHelper,
    viewModel: SplashViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    val state = viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            coroutineScope.async {
                firebaseDynamicLinkHelper.handleDynamicLinks {
                    viewModel.setInvitedProjectId(it)
                }
            }.await()

            coroutineScope.async { viewModel.initLogin() }.await()
        }
    }

    LaunchedEffect(viewModel.singleEventFlow) {
        viewModel.singleEventFlow
            .onEach { event ->
                when (event) {
                    SplashSingleEvent.NavigateToMain -> {
                        (context as Activity).startActivityWithAnimation<MainActivity>(
                            withFinish = true
                        )
                    }

                    SplashSingleEvent.NavigateToCreateProject -> {
                        (context as Activity).startActivityWithAnimation<CreateProjectActivity>(
                            withFinish = true
                        )
                    }

                    SplashSingleEvent.RenewAccessToken -> {
                        viewModel.renewAccessToken()
                    }

                    SplashSingleEvent.NavigateToLogin -> {
                        (context as Activity).startActivityWithAnimation<IntroActivity>(
                            withFinish = true
                        )
                    }
                }
            }
            .launchIn(this)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(listOf(Color.White, Purple200))
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

    }
}