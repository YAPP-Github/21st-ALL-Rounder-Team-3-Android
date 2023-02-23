package com.yapp.timitimi.presentation.web.feedback

import androidx.lifecycle.SavedStateHandle
import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.presentation.constant.Extras
import com.yapp.timitimi.presentation.web.webview.WebViewViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    preference: UserPreference
) : WebViewViewModel(preference) {

    override fun setUrl() {
        val projectId = savedStateHandle.getStateFlow(Extras.ProjectId, -1).value
        val taskId = savedStateHandle.getStateFlow(Extras.TaskId, -1).value
        _url.value = getUrl(projectId, taskId)
    }

    private companion object {
        fun getUrl(projectId: Int, taskId: Int) = "/project/$projectId/task/$taskId/feedback"
    }
}