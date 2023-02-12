package com.yapp.timitimi.domain.respository

import com.yapp.timitimi.domain.entity.UserProfile
import kotlinx.coroutines.flow.Flow

interface MemberRepository {
    suspend fun getUserInfo(): Flow<UserProfile>
}