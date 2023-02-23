package com.yapp.timitimi.presentation.web.taskdetail

import android.webkit.JavascriptInterface
import com.yapp.timitimi.presentation.web.edittask.EditTaskParam
import com.yapp.timitimi.presentation.web.webview.Bridge
import timber.log.Timber

class TaskDetailBridge(
    val onNavigateToMain: () -> Unit,
    val onNavigateToEdit: (Int, Int, EditTaskParam) -> Unit,
    val onNavigateToFeedback: (Int, Int) -> Unit,
) : Bridge {
    @JavascriptInterface
    fun navigateToMain() {
        onNavigateToMain()
    }

    @JavascriptInterface
    fun navigateToEdit(
        projectId: Int,
        taskId: Int,
        managerId: String,
        managerValue: String,
        title: String,
        memo: String,
        startDate: String,
        dueDate: String
    ) {
        onNavigateToEdit(
            projectId, taskId,
            EditTaskParam(managerId, managerValue, title, memo, startDate, dueDate)
        )
    }

    @JavascriptInterface
    fun navigateToFeedback(projectId: Int, taskId: Int) {
        onNavigateToFeedback(projectId, taskId)
    }
}