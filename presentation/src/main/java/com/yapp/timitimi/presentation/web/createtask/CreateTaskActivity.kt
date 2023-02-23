@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.yapp.timitimi.presentation.web.createtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yapp.timitimi.presentation.web.WebViewScreen
import com.yapp.timitimi.theme.AllRounder3Theme
import com.yapp.timitimi.ui.finishWithAnimation
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CreateTaskActivity : ComponentActivity() {

    private val viewModel: CreateTaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val url by viewModel.url.collectAsStateWithLifecycle()
            val accessToken by viewModel.accessToken.collectAsStateWithLifecycle()
            LaunchedEffect(key1 = Unit) {
                viewModel.init()
            }
            AllRounder3Theme {
                Timber.e(accessToken)
                if (url.isNotEmpty() && accessToken.isNotEmpty()) {
                    WebViewScreen(
                        urlParam = url,
                        accessToken = accessToken,
                        bridge = CreateTaskBridge {
                            finishWithAnimation()
                        }
                    )
                }
            }
        }
    }
}
