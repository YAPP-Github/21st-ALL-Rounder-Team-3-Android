package com.yapp.timitimi.presentation.ui.invite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import com.yapp.timitimi.presentation.helper.FirebaseDynamicLinkHelper
import com.yapp.timitimi.presentation.ui.createproject.redux.CreateProjectSingleEvent
import com.yapp.timitimi.presentation.ui.createproject.screen.CreateProjectTwoStepScreen
import com.yapp.timitimi.presentation.ui.createproject.viewmodel.CreateProjectViewModel
import com.yapp.timitimi.presentation.ui.main.MainActivity
import com.yapp.timitimi.theme.AllRounder3Theme
import com.yapp.timitimi.ui.startActivityWithAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class InviteUserActivity: ComponentActivity() {
    val viewModel: CreateProjectViewModel by viewModels()

    @Inject
    lateinit var firebaseDynamicLinkHelper: FirebaseDynamicLinkHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllRounder3Theme {
                LaunchedEffect(viewModel.singleEventFlow) {
                    viewModel.singleEventFlow
                        .onEach { event ->
                            when (event) {
                                CreateProjectSingleEvent.NavigateToMain -> {
                                    viewModel.saveLastViewedProjectId()
                                    startActivityWithAnimation<MainActivity>(
                                        withFinish = true
                                    )
                                }

                                is CreateProjectSingleEvent.NavigateToTwoStepPage,
                                CreateProjectSingleEvent.NavigateOneStopPage,
                                CreateProjectSingleEvent.Exit -> {
                                   finish()
                                }

                                is CreateProjectSingleEvent.ShowChooser -> {
                                    firebaseDynamicLinkHelper.createDynamicLink(event.id)
                                }
                            }
                        }
                        .launchIn(this)
                }


                CreateProjectTwoStepScreen(
                    viewModel
                ) {
                    finish()
                }
            }
        }
    }
}