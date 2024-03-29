package com.yapp.timitimi.data.repository

import com.yapp.timitimi.data.api.TimiServiceApi
import com.yapp.timitimi.data.base.apiCall
import com.yapp.timitimi.data.request.toData
import com.yapp.timitimi.data.response.toDomain
import com.yapp.timitimi.domain.entity.LoginInfo
import com.yapp.timitimi.domain.entity.LoginProviderInfo
import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.domain.respository.OAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OAuthRepositoryImpl @Inject constructor(
    private val timiService: TimiServiceApi,
    private val userPreference: UserPreference
) : OAuthRepository {
    override suspend fun refreshUserToken(): Flow<Result<Boolean>> {
        return apiCall(
            call = {
                timiService.refreshUserToken(
                    userPreference.accessToken,
                    userPreference.refreshToken
                )
            },
            mapper = { it }
        )
    }

    override suspend fun login(body: LoginProviderInfo): Flow<Result<LoginInfo>> {
        return apiCall(
            call = {
                timiService.login(body.toData())
            },
            mapper = {
                it.toDomain()
            }
        )
    }
}