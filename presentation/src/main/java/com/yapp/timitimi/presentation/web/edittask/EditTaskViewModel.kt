package com.yapp.timitimi.presentation.web.edittask

import androidx.lifecycle.SavedStateHandle
import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.presentation.constant.Extras
import com.yapp.timitimi.presentation.web.webview.WebViewViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    preference: UserPreference
) : WebViewViewModel(preference) {

    override fun setUrl() {
        val projectId = savedStateHandle.getStateFlow(Extras.ProjectId, -1).value
        val taskId = savedStateHandle.getStateFlow(Extras.TaskId, -1).value
        val editTaskParam = savedStateHandle.getStateFlow(Extras.EditTask, EditTaskParam.empty()).value
        _url.value = getUrl(projectId, taskId, editTaskParam)
        Timber.e("데이터 잘 받았는가 수정페이지 $projectId $taskId")
    }

    private companion object {
        fun getUrl(
            projectId: Int,
            taskId: Int,
            editTaskParam: EditTaskParam
        ) = with(editTaskParam) {
            "/project/$projectId/task/$taskId/edit?managerId=$managerId&managerValue=$managerValue&title=$title&memo=$memo&startDate=$startDate&dueDate=$dueDate"
        }
    }
}

data class EditTaskParam(
    val managerId: String,
    val managerValue: String,
    val title: String,
    val memo: String,
    val startDate: String,
    val dueDate: String,
) : Serializable {
    companion object {
        fun empty() = EditTaskParam(
            managerId = "",
            managerValue = "",
            title = "",
            memo = "",
            startDate = "",
            dueDate = "",
        )
    }
}
