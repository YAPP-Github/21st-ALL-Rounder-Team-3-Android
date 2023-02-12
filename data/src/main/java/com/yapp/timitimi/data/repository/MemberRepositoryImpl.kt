package com.yapp.timitimi.data.repository

import com.yapp.timitimi.data.api.TimiServiceApi
import com.yapp.timitimi.data.base.apiCall
import com.yapp.timitimi.data.response.toDomain
import com.yapp.timitimi.domain.entity.UserProfile
import com.yapp.timitimi.domain.respository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val timiService: TimiServiceApi
): MemberRepository {
    override suspend fun getUserInfo(): Flow<UserProfile> {
        return apiCall(
            call = { timiService.getUserInfo() },
            mapper = { data -> data.toDomain() }
        )
    }
}