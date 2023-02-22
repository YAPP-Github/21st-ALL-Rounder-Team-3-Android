package com.yapp.timitimi.data.repository

import com.yapp.timitimi.data.api.TimiServiceApi
import com.yapp.timitimi.data.base.apiCall
import com.yapp.timitimi.data.response.toDomain
import com.yapp.timitimi.domain.entity.Participant
import com.yapp.timitimi.domain.respository.ParticipantsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ParticipantsRepositoryImpl @Inject constructor(
    private val timiService: TimiServiceApi
) : ParticipantsRepository {
    override suspend fun postParticipants(projectId: String): Flow<Unit> {
        return timiService.postProjectParticipants(projectId)
    }

    override suspend fun getProjectParticipants(projectId: Int): Flow<List<Participant>> {
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