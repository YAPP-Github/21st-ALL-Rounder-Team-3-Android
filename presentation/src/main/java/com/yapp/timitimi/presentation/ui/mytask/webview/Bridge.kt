package com.yapp.timitimi.presentation.ui.mytask.webview

import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import com.yapp.timitimi.presentation.ui.mytask.AccessTokenKey
import com.yapp.timitimi.presentation.ui.mytask.AccessTokenValue
import com.yapp.timitimi.presentation.ui.mytask.Url
import timber.log.Timber

const val BaseUrl = "http://10.0.2.2:8081"

class MyTaskWebViewBridge(
    private val startMain: () -> Unit,
    private val updateUrl: (String) -> Unit,
) {

    @JavascriptInterface
    fun navigateToMain() {
        startMain()
    }

    @JavascriptInterface
    fun navigateToEdit(
        projectId: String,
        taskId: String,
    ) {
        updateUrl("$BaseUrl/project/$projectId/task/$taskId")
    }

    @JavascriptInterface
    fun navigateToFeedback(
        projectId: String,
        taskId: String,
    ) {
        updateUrl("$BaseUrl/project/$projectId/task/$taskId/feedback")
    }

    @JavascriptInterface
    fun navigateToMyTask(
        projectId: String,
        taskId: String,
    ) {
        updateUrl("$BaseUrl/project/$projectId/task/my/$taskId")// /project/{{projectId}}/task/my/{{taskId}}
    }

    @JavascriptInterface
    fun navigateToOtherTask(
        projectId: String,
        taskId: String,
    ) {
        updateUrl("$BaseUrl/project/$projectId/task/$taskId")
    }

    companion object {
        const val Name = "Android"
    }
}