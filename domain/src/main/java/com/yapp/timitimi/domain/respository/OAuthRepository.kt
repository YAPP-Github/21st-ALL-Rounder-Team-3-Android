package com.yapp.timitimi.domain.respository

import kotlinx.coroutines.flow.Flow

interface OAuthRepository {
    suspend fun refreshUserToken(): Flow<Result<Boolean>>
}