package com.yapp.timitimi.presentation.web.edittask

import android.webkit.JavascriptInterface
import com.yapp.timitimi.presentation.web.webview.Bridge

interface EditTaskBridge : Bridge {
    @JavascriptInterface
    fun navigateToMyTask(projectId: Int, taskId: Int)
}