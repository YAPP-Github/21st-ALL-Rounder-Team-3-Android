@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.yapp.timitimi.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yapp.timitimi.presentation.ui.main.redux.MainIntent
import com.yapp.timitimi.presentation.ui.main.redux.ScreenStep
import com.yapp.timitimi.presentation.ui.main.screen.MainContainerScreen
import com.yapp.timitimi.presentation.ui.main.screen.guide.GuideScreen
import com.yapp.timitimi.theme.AllRounder3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dispatch(MainIntent.CheckNewUser())
        setContent {
            val uiController = rememberSystemUiController()
            val state by viewModel.viewState.collectAsStateWithLifecycle()

            AllRounder3Theme {
                Crossfade(targetState = state.currentStep) { currentStep ->
                    when (currentStep) {
                        ScreenStep.First -> {
                            GuideScreen(
                                onClose = {
                                    viewModel.dispatch(MainIntent.ClickGuideScreen)
                                },
                                currentStep = currentStep
                            )
                        }

                        ScreenStep.Second -> {
                            GuideScreen(
                                onClose = {
                                    viewModel.dispatch(MainIntent.ClickGuideScreen)
                                },
                                currentStep = currentStep,
                            )
                        }

                        ScreenStep.Main -> {
                            SideEffect {
                                uiController.setStatusBarColor(
                                    color = Color.White,
                                )
                            }
                            MainContainerScreen()
                        }
                    }
                }
            }
        }
    }
}


