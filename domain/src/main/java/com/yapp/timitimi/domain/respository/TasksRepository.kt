package com.yapp.timitimi.domain.respository

interface TasksRepository {
    /**
     * 프로젝트 내 테스크 생성
     */
    suspend fun postProjectTasks(projectId: String)

    /**
     * 프로젝트 내 테스크 리스트 조회
     */
    suspend fun getProjectTasks(projectId: String)

    /**
     * 프로젝트 내 테스크 정보 조회
     */
    suspend fun getTasks(taskId: String)
}
