package com.yapp.timitimi.presentation.web.feedback

import android.webkit.JavascriptInterface
import com.yapp.timitimi.presentation.web.webview.Bridge

interface FeedbackBridge : Bridge {
    @JavascriptInterface
    fun navigateToOtherTask(projectId: Int, taskId: Int)
}