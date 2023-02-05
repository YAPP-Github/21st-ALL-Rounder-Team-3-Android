package com.yapp.timitimi.domain.respository

import com.yapp.timitimi.domain.entity.CreateProjectsInfo

interface ProjectsRepository {
    /**
     * 프로젝트 리스트 조회
     */
    suspend fun getAllProject()

    /**
     * 프로젝트 생성
     */
    suspend fun postProjects(body: CreateProjectsInfo)

    /**
     * 프로젝트 정보 조회
     */
    suspend fun getProject(projectId: String)
}
