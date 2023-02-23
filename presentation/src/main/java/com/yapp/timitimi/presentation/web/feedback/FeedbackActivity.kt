@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.yapp.timitimi.presentation.web.feedback

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yapp.timitimi.presentation.constant.Extras
import com.yapp.timitimi.presentation.web.WebViewScreen
import com.yapp.timitimi.presentation.web.taskdetail.TaskDetailActivity
import com.yapp.timitimi.theme.AllRounder3Theme
import com.yapp.timitimi.ui.startActivityWithAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedbackActivity : ComponentActivity() {

    private val viewModel: FeedbackViewModel by viewModels()

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
                    bridge = object : FeedbackBridge {
                        override fun navigateToOtherTask(projectId: Int, taskId: Int) {
                            startActivityWithAnimation<TaskDetailActivity>(
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