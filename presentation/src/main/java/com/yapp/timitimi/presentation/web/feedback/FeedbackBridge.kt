package com.yapp.timitimi.presentation.web.feedback

import android.webkit.JavascriptInterface
import com.yapp.timitimi.presentation.web.webview.Bridge

class FeedbackBridge(
    val navigateToOtherTask: (projectId: Int, taskId: Int) -> Unit
) : Bridge {
    @JavascriptInterface
    fun navigateToOtherTask(projectId: Int, taskId: Int) {
        navigateToOtherTask(projectId, taskId)
    }
}