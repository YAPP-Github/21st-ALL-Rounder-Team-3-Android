package com.yapp.timitimi.presentation.ui.projectlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yapp.timitimi.domain.respository.ProjectsRepository
import com.yapp.timitimi.presentation.ui.projectlist.model.toAllProjectItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectListViewModel @Inject constructor(
    private val projectsRepository: ProjectsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<ProjectListUiState>(ProjectListUiState.UnInitialized)
    val uiState: StateFlow<ProjectListUiState> = _uiState

    init {
        loadAllProject()
    }

    private fun loadAllProject() {
        viewModelScope.launch {
            projectsRepository.getAllProject()
                .onEach { list ->
                    _uiState.value = ProjectListUiState.Succeed(
                        list.mapIndexed { index, project ->
                            project.toAllProjectItem(index)
                        }
                    )
                }
                .catch {
                    _uiState.value = ProjectListUiState.Error
                }
                .collect()
        }
    }
}