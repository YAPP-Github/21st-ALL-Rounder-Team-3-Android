package com.yapp.domain.respository

interface ParticipantsRepository {
    /**
     * 참여자 추가
     */
    suspend fun postParticipants(projectId: String)

    /**
     * 참여자 정보 리스트 조회
     */
    suspend fun getProjectParticipants(projectId: String)

    /**
     * 참여자 정보 조회
     */
    suspend fun getParticipants()
}
