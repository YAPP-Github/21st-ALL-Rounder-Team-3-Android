package com.yapp.timitimi.data.repository

import com.yapp.timitimi.data.api.TimiServiceApi
import com.yapp.timitimi.data.base.apiCall
import com.yapp.timitimi.data.response.ParticipantResponse
import com.yapp.timitimi.data.response.toDomain
import com.yapp.timitimi.domain.entity.Participant
import com.yapp.timitimi.domain.respository.ParticipantsRepository
import javax.inject.Inject

class ParticipantsRepositoryImpl @Inject constructor(
    private val timiService: TimiServiceApi
) : ParticipantsRepository {
    override suspend fun postParticipants(projectId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getProjectParticipants(projectId: String): Result<List<Participant>> {
        return apiCall(
            call = { timiService.getProjectParticipants(projectId) },
            mapper = { data ->
                data.map { it.toDomain() }
            }
        )
    }



    override suspend fun getParticipants() {
        TODO("Not yet implemented")
    }
}