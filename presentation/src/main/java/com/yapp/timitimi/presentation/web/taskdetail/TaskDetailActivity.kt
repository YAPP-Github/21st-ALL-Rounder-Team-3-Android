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
import timber.log.Timber

@AndroidEntryPoint
class TaskDetailActivity : ComponentActivity() {

    private val viewModel: TaskDetailViewModel by viewModels()

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
                        bridge = TaskDetailBridge(
                            onNavigateToEdit = { projectId, taskId, editTaskParam ->
                                Timber.e("데이터 잘 보내는가 $projectId $taskId")
                                startActivityWithAnimation<EditTaskActivity>(
                                    intentBuilder = {
                                        putExtra(Extras.ProjectId, projectId)
                                        putExtra(Extras.TaskId, taskId)
                                        putExtra(Extras.EditTask, editTaskParam)
                                    }
                                )
                            },
                            onNavigateToFeedback = { projectId, taskId ->
                                startActivityWithAnimation<FeedbackActivity>(
                                    intentBuilder = {
                                        putExtra(Extras.ProjectId, projectId)
                                        putExtra(Extras.TaskId, taskId)
                                    }
                                )
                            },
                            onNavigateToMain = {
                                startActivityWithAnimation<MainActivity>()
                            }
                        )
                    )
                }
            }
        }
    }
}