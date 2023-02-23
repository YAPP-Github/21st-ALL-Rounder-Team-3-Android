package com.yapp.timitimi.presentation.web.taskdetail

import android.webkit.JavascriptInterface
import com.yapp.timitimi.presentation.web.webview.Bridge

interface TaskDetailBridge : Bridge {
    @JavascriptInterface
    fun navigateToMain()

    @JavascriptInterface
    fun navigateToEdit(projectId: Int, taskId: Int)

    @JavascriptInterface
    fun navigateToFeedback(projectId: Int, taskId: Int)
}