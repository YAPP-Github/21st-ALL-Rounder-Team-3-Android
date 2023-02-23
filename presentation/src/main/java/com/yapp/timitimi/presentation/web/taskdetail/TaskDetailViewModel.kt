package com.yapp.timitimi.presentation.web.taskdetail

import androidx.lifecycle.SavedStateHandle
import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.presentation.constant.Extras
import com.yapp.timitimi.presentation.web.webview.WebViewViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class TaskDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    preference: UserPreference
) : WebViewViewModel(preference) {

    override fun setUrl() {
        val projectId = savedStateHandle.getStateFlow(Extras.ProjectId, -1).value
        val taskId = savedStateHandle.getStateFlow(Extras.TaskId, -1).value
        val isMe = savedStateHandle.getStateFlow(Extras.IsMe, false).value
        _url.value = getUrl(isMe, taskId, projectId)
    }

    private companion object {
        fun getUrl(
            isMe: Boolean,
            projectId: Int,
            taskId: Int,
        ) = if (isMe) {
            "/project/$projectId/task/my/$taskId"
        } else {
            "/project/$projectId/task/$taskId"
        }
    }
}