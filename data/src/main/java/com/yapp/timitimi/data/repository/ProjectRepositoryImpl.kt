package com.yapp.timitimi.data.repository

import com.yapp.timitimi.data.api.TimiServiceApi
import com.yapp.timitimi.data.base.apiCall
import com.yapp.timitimi.data.request.PostProjectsBody
import com.yapp.timitimi.data.response.toDomain
import com.yapp.timitimi.domain.entity.CreateProjectsInfo
import com.yapp.timitimi.domain.entity.Project
import com.yapp.timitimi.domain.respository.ProjectsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val timiService: TimiServiceApi
) : ProjectsRepository {
    override suspend fun getAllProject(): Flow<List<Project>> {
        return apiCall(
            call = { timiService.getAllProject() },
            mapper = { data ->
                data.map { it.toDomain() }
            }
        )
    }

    override suspend fun postProjects(body: CreateProjectsInfo): Flow<Int> {
        return apiCall(
            call = {
                timiService.postProjects(
                    PostProjectsBody(
                        body.name,
                        body.startDate,
                        body.dueDate,
                        body.goal,
                        body.difficulty,
                        body.projectStatus
                    )
                )
            },
            mapper = { data ->
                data.projectId
            }
        )
    }

    override suspend fun getProject(projectId: String) {
        TODO("Not yet implemented")
    }
}