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
        projectId: String,
        taskId: String,
        managerId: String,
        managerValue: String,
        title: String,
        memo: String,
        startDate: String,
        dueDate: String
    ) {
        Timber.e("데이터 잘 받아왔는가 $projectId $taskId")
        onNavigateToEdit(
            projectId.toInt(), taskId.toInt(),
            EditTaskParam(managerId, managerValue, title, memo, startDate, dueDate)
        )
    }

    @JavascriptInterface
    fun navigateToFeedback(projectId: Int, taskId: Int) {
        onNavigateToFeedback(projectId, taskId)
    }
}