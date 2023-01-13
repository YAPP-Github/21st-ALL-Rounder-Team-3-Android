package com.yapp.timitimi.domain.respository

import com.yapp.timitimi.domain.entity.Participant

interface ParticipantsRepository {
    /**
     * 참여자 추가
     */
    suspend fun postParticipants(projectId: String)

    /**
     * 참여자 목록 조회
     */
    suspend fun getProjectParticipants(projectId: String): Result<List<Participant>>

    /**
     * 참여자 정보 조회
     */
    suspend fun getParticipants()
}
