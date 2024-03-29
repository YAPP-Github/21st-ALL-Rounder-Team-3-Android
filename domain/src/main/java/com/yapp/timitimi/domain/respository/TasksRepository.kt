package com.yapp.timitimi.domain.respository

import com.yapp.timitimi.domain.entity.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    /**
     * 프로젝트 내 테스크 생성
     */
    suspend fun postProjectTasks(projectId: String)

    /**
     * 프로젝트 내 테스크 리스트 조회
     */
    suspend fun getProjectTasks(projectId: Int): Flow<Result<List<Task>>>

    /**
     * 프로젝트 내 테스크 정보 조회
     */
    suspend fun getTasks(taskId: String)
}
