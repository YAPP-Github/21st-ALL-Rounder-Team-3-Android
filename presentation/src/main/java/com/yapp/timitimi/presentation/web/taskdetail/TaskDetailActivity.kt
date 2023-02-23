@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.yapp.timitimi.presentation.web.taskdetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yapp.timitimi.presentation.constant.Extras
import com.yapp.timitimi.presentation.ui.main.MainActivity
import com.yapp.timitimi.presentation.web.WebViewScreen
import com.yapp.timitimi.presentation.web.edittask.EditTaskActivity
import com.yapp.timitimi.presentation.web.feedback.FeedbackActivity
import com.yapp.timitimi.theme.AllRounder3Theme
import com.yapp.timitimi.ui.startActivityWithAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskDetailActivity : ComponentActivity() {

    private val viewModel: TaskDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(key1 = Unit) {
                viewModel.init()
            }
            AllRounder3Theme {
                val url by viewModel.url.collectAsStateWithLifecycle()
                val accessToken by viewModel.accessToken.collectAsStateWithLifecycle()
                WebViewScreen(
                    urlParam = url,
                    accessToken = accessToken,
                    bridge = object : TaskDetailBridge {
                        override fun navigateToMain() {
                            startActivityWithAnimation<MainActivity>()
                        }

                        override fun navigateToEdit(projectId: Int, taskId: Int) {
                            startActivityWithAnimation<EditTaskActivity>(
                                intentBuilder = {
                                    putExtra(Extras.ProjectId, projectId)
                                    putExtra(Extras.TaskId, taskId)
                                }
                            )
                        }

                        override fun navigateToFeedback(projectId: Int, taskId: Int) {
                            startActivityWithAnimation<FeedbackActivity>(
                                intentBuilder = {
                                    putExtra(Extras.ProjectId, projectId)
                                    putExtra(Extras.TaskId, taskId)
                                }
                            )
                        }
                    }
                )
            }
        }
    }
}