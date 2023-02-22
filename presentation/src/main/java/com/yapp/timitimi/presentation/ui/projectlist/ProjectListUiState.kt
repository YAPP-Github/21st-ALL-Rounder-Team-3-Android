package com.yapp.timitimi.presentation.ui.projectlist

import com.yapp.timitimi.presentation.ui.projectlist.model.ProjectListItem

sealed class ProjectListUiState {
    object UnInitialized: ProjectListUiState()
    data class Succeed(
        val list: List<ProjectListItem>
    ): ProjectListUiState()
    object Error: ProjectListUiState()
}