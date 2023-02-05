package com.yapp.timitimi.data.repository

import com.yapp.timitimi.data.api.TimiServiceApi
import com.yapp.timitimi.data.request.PostProjectsBody
import com.yapp.timitimi.domain.entity.CreateProjectsInfo
import com.yapp.timitimi.domain.respository.ProjectsRepository
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val timiService: TimiServiceApi
): ProjectsRepository {
    override suspend fun getAllProject() {
        TODO("Not yet implemented")
    }

    override suspend fun postProjects(body: CreateProjectsInfo) {
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

    override suspend fun getProject(projectId: String) {
        TODO("Not yet implemented")
    }
}