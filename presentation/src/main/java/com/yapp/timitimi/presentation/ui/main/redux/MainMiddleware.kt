@file:OptIn(FlowPreview::class)

package com.yapp.timitimi.presentation.ui.main.redux

import com.yapp.timitimi.domain.entity.Project
import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.domain.respository.ParticipantsRepository
import com.yapp.timitimi.domain.respository.ProjectsRepository
import com.yapp.timitimi.domain.respository.TasksRepository
import com.yapp.timitimi.redux.BaseMiddleware
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import timber.log.Timber
import javax.inject.Inject

class MainMiddleware @Inject constructor(
    private val participantsRepository: ParticipantsRepository,
    private val projectsRepository: ProjectsRepository,
    private val tasksRepository: TasksRepository,
    private val userPreference: UserPreference,
) : BaseMiddleware<MainIntent, MainSingleEvent> {


    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<MainIntent>,
        eventFlow: MutableSharedFlow<MainSingleEvent>
    ): Flow<MainIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<MainIntent.CheckNewUser>()
                    .flatMapConcat { intent ->
                        userPreference.getIsFirstProject().map {
                            intent.copy(
                                isFirstUser = it
                            )
                        }
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.Init>()
                    .onEach {
                        Timber.e(it.toString())
                    }
                    .flatMapConcat { intent ->
                        projectsRepository.getProject(intent.projectId)
                            .map { result ->
                                intent.copy(
                                    project = result.getOrDefault(Project.empty())
                                )
                            }
                    }
                    .flatMapConcat { intent ->
                        participantsRepository.getProjectParticipants(intent.projectId)
                            .map { result ->
                                intent.copy(
                                    participants = result.getOrDefault(emptyList())
                                        .toImmutableList()
                                )
                            }
                    }
                    .flatMapConcat { intent ->
                        tasksRepository.getProjectTasks(intent.projectId)
                            .map { result ->
                                intent.copy(
                                    tasks = result.getOrDefault(emptyList()).toImmutableList()
                                )
                            }
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.ClickBackButton>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(MainSingleEvent.NavigateToProjectList)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.ClickEditButton>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(MainSingleEvent.NavigateToEditProject(it.projectId))
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.ClickNotificationButton>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(MainSingleEvent.NavigateToNotificationList)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.SelectProfile>()
                    .onEach {
                        Timber.e(it.toString())
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.SelectAddProfile>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(MainSingleEvent.NavigateToInviteMember)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.ClickFab>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(MainSingleEvent.NavigateToCreateTask(projectId = it.projectId))
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<MainIntent.ClickTaskItem>()
                    .onEach {
                        Timber.e(it.toString())
                        eventFlow.emit(
                            MainSingleEvent.NavigateToTaskDetail(
                                projectId = it.projectId,
                                taskId = it.taskId,
                                isMe = it.isMe,
                            )
                        )
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed())
            )
        }
    }
}
