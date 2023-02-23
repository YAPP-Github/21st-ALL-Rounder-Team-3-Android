package com.yapp.timitimi.data.repository

import com.yapp.timitimi.data.api.TimiServiceApi
import com.yapp.timitimi.data.base.apiCall
import com.yapp.timitimi.data.request.PostProjectsBody
import com.yapp.timitimi.data.request.toData
import com.yapp.timitimi.data.response.toDomain
import com.yapp.timitimi.domain.entity.CreateProjectsInfo
import com.yapp.timitimi.domain.entity.EditProjectInfo
import com.yapp.timitimi.domain.entity.Project
import com.yapp.timitimi.domain.respository.ProjectsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val timiService: TimiServiceApi
) : ProjectsRepository {
    override suspend fun getAllProject(): Flow<Result<List<Project>>> {
        return apiCall(
            call = { timiService.getAllProject() }
        ) { data ->
            data.map { it.toDomain() }
        }
    }

    override suspend fun postProjects(body: CreateProjectsInfo): Flow<Result<Int>> {
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
            }
        ) { data ->
            data.projectId
        }
    }

    override suspend fun getProject(projectId: Int): Flow<Result<Project>> {
        return apiCall(
            call = {
                timiService.getProject(projectId)
            }
        ) { data ->
            data.toDomain()
        }
    }

    override suspend fun putProject(projectId: Int, body: EditProjectInfo): Flow<Result<String>> {
        return apiCall(
            call = {
                timiService.putProject(projectId, body.toData())
            }
        ) { it }
    }
}