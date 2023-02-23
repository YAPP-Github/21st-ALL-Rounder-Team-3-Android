package com.yapp.timitimi.data.repository

import com.yapp.timitimi.data.api.TimiServiceApi
import com.yapp.timitimi.data.base.apiCall
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
}