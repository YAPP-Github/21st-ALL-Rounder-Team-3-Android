package com.yapp.data.repository

import com.yapp.data.api.TimiServiceApi
import com.yapp.domain.respository.FeedbackRepository
import javax.inject.Inject

class FeedbackRepositoryImpl @Inject constructor(
    private val timiService: TimiServiceApi
): FeedbackRepository {
    override suspend fun postTaskFeedbacks(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskFeedbacks(projectId: String) {
        TODO("Not yet implemented")
    }
}