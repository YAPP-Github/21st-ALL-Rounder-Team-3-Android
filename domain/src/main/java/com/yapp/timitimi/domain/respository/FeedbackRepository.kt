package com.yapp.timitimi.domain.respository

interface FeedbackRepository {
    /**
     * 피드백 생성
     */
    suspend fun postTaskFeedbacks(taskId: String)

    /**
     * 피드백 리스트 조회
     */
    suspend fun getTaskFeedbacks(projectId: String)
}
