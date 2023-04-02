package com.yapp.timitimi.domain.respository

import com.yapp.timitimi.domain.entity.LoginInfo
import com.yapp.timitimi.domain.entity.LoginProviderInfo
import kotlinx.coroutines.flow.Flow

interface OAuthRepository {
    suspend fun refreshUserToken(): Flow<Result<Boolean>>
    suspend fun login(body: LoginProviderInfo): Flow<Result<LoginInfo>>
}