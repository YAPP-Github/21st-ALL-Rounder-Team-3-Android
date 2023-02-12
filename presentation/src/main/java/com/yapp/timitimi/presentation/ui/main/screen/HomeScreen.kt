package com.yapp.timitimi.presentation.ui.createproject

import android.app.Activity
import android.content.Context
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.ui.main.GuideImage
import com.yapp.timitimi.presentation.ui.main.MainViewModel
import com.yapp.timitimi.presentation.ui.main.redux.MainIntent
import com.yapp.timitimi.presentation.ui.main.redux.ScreenStep
import com.yapp.timitimi.presentation.ui.main.screen.MainScreen

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Crossfade(targetState = state.currentStep) { currentStep ->
        when (currentStep) {
            ScreenStep.First -> { //TODO(EvergreenTree97) : 더미 데이터와, DIM 처리로 변경해야함 WIP
                GuideImage(
                    onClick = { viewModel.dispatch(MainIntent.ClickGuideScreen) },
                    painter = painterResource(id = R.drawable.main_guide_first),
                    contentDescription = "first guide page"
                )
            }

            ScreenStep.Second -> {
                GuideImage(
                    onClick = { viewModel.dispatch(MainIntent.ClickGuideScreen) },
                    painter = painterResource(id = R.drawable.main_guide_second),
                    contentDescription = "second guide page"
                )
            }

            ScreenStep.Main -> {
                MainScreen(
                    onBackPressed = {
                        (context as Activity).finish()
                    }
                )
            }
        }
    }
}