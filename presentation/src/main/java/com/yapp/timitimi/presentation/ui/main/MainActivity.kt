@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.yapp.timitimi.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.ui.main.redux.MainIntent
import com.yapp.timitimi.presentation.ui.main.redux.ScreenStep
import com.yapp.timitimi.presentation.ui.main.screen.MainScreen
import com.yapp.timitimi.theme.AllRounder3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dispatch(MainIntent.CheckNewUser())
        setContent {
            val state by viewModel.viewState.collectAsStateWithLifecycle()

            AllRounder3Theme {
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
                                    finish()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GuideImage(
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String,
) {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .timiClickable(
                onClick = onClick,
                rippleEnabled = false
            ),
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.FillBounds,
    )
}