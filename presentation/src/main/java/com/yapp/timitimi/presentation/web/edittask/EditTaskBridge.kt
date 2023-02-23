package com.yapp.timitimi.presentation.web.edittask

import android.webkit.JavascriptInterface
import com.yapp.timitimi.presentation.web.webview.Bridge

class EditTaskBridge(
    val onNavigateToMyTask: (projectId: Int, taskId: Int) -> Unit,
) : Bridge {
    @JavascriptInterface
    fun navigateToMyTask(projectId: Int, taskId: Int){
        onNavigateToMyTask(projectId, taskId)
    }
}