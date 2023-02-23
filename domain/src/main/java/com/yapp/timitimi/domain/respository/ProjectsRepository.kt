package com.yapp.timitimi.domain.respository

import com.yapp.timitimi.domain.entity.CreateProjectsInfo
import com.yapp.timitimi.domain.entity.EditProjectInfo
import com.yapp.timitimi.domain.entity.Project
import kotlinx.coroutines.flow.Flow

interface ProjectsRepository {
    /**
     * 프로젝트 리스트 조회
     */
    suspend fun getAllProject(): Flow<Result<List<Project>>>

    /**
     * 프로젝트 생성
     */
    suspend fun postProjects(body: CreateProjectsInfo): Flow<Result<Int>>

    /**
     * 프로젝트 정보 조회
     */
    suspend fun getProject(projectId: Int): Flow<Result<Project>>

    /**
     * 프로젝트 수정
     */
    suspend fun putProject(
        projectId: Int,
        body: EditProjectInfo,
    ): Flow<Result<String>>
}
