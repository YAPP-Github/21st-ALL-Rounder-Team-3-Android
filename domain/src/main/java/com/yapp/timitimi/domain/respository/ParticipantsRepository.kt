package com.yapp.timitimi.domain.respository

import com.yapp.timitimi.domain.entity.Participant
import kotlinx.coroutines.flow.Flow

interface ParticipantsRepository {
    /**
     * 참여자 추가
     */
    suspend fun postParticipants(projectId: String)

    /**
     * 참여자 목록 조회
     */
    suspend fun getProjectParticipants(projectId: Int): Flow<List<Participant>>

    /**
     * 참여자 정보 조회
     */
    suspend fun getParticipants()
}
