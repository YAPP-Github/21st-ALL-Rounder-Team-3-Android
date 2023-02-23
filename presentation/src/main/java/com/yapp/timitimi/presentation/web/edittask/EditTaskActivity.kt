@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.yapp.timitimi.presentation.web.edittask

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
class EditTaskActivity : ComponentActivity() {

    private val viewModel: EditTaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val url by viewModel.url.collectAsStateWithLifecycle()
            val accessToken by viewModel.accessToken.collectAsStateWithLifecycle()
            LaunchedEffect(key1 = Unit) {
                viewModel.init()
            }
            AllRounder3Theme {
                if (url.isNotEmpty() && accessToken.isNotEmpty()) {
                    WebViewScreen(
                        urlParam = url,
                        accessToken = accessToken,
                        bridge = EditTaskBridge { projectId, taskId ->
                            startActivityWithAnimation<TaskDetailActivity>(
                                intentBuilder = {
                                    putExtra(Extras.ProjectId, projectId)
                                    putExtra(Extras.TaskId, taskId)
                                    putExtra(Extras.IsMe, true)
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}