package com.yapp.data.repository

import com.yapp.data.api.TimiServiceApi
import com.yapp.domain.respository.ParticipantsRepository
import javax.inject.Inject

class ParticipantsRepositoryImpl @Inject constructor(
    private val timiService: TimiServiceApi
): ParticipantsRepository {
    override suspend fun postParticipants(projectId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getProjectParticipants(projectId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getParticipants() {
        TODO("Not yet implemented")
    }
}