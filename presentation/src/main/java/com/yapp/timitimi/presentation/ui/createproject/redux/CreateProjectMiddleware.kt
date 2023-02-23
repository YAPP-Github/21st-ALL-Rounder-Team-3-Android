package com.yapp.timitimi.presentation.ui.createproject.redux

import com.yapp.timitimi.domain.respository.ProjectsRepository
import com.yapp.timitimi.redux.BaseMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import timber.log.Timber
import javax.inject.Inject

class CreateProjectMiddleware @Inject constructor(
    private val projectsRepository: ProjectsRepository
) : BaseMiddleware<CreateProjectIntent, CreateProjectSingleEvent> {
    override fun mutate(
        scope: CoroutineScope,
        intentFlow: Flow<CreateProjectIntent>,
        eventFlow: MutableSharedFlow<CreateProjectSingleEvent>
    ): Flow<CreateProjectIntent> {
        return intentFlow.run {
            merge(
                filterIsInstance<CreateProjectIntent.ChangeProjectName>()
                    .onEach {
                        Timber.e(it.toString())
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<CreateProjectIntent.ShareProjectDeeplink>()
                    .onEach {
                        eventFlow.emit(CreateProjectSingleEvent.ShowChooser(it.projectId))
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<CreateProjectIntent.ClickBackButton>()
                    .onEach {
                        val event = if (it.progress == 1f) {
                            CreateProjectSingleEvent.NavigateOneStopPage
                        } else {
                            CreateProjectSingleEvent.Exit
                        }
                        eventFlow.emit(event)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<CreateProjectIntent.ClickNextButton>()
                    .onEach {
                        Timber.e(it.toString())
                        projectsRepository.postProjects(it.state.toCreateProjectsInfoEntity())
                            .onEach { result ->
                                Timber.e(result.toString())
                                eventFlow.emit(
                                    CreateProjectSingleEvent.NavigateToTwoStepPage(
                                        result.getOrDefault(0).toString()
                                    )
                                )
                            }
                            .launchIn(scope)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed()),

                filterIsInstance<CreateProjectIntent.StartMain>()
                    .onEach {
                        eventFlow.emit(CreateProjectSingleEvent.NavigateToMain)
                    }
                    .shareIn(scope, SharingStarted.WhileSubscribed())
            )
        }
    }
}
