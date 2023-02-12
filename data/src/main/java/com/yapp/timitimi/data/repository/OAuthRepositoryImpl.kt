package com.yapp.timitimi.data.repository

import com.yapp.timitimi.data.api.TimiServiceApi
import com.yapp.timitimi.domain.preference.UserPreference
import com.yapp.timitimi.domain.respository.OAuthRepository
import javax.inject.Inject

class OAuthRepositoryImpl @Inject constructor(
    private val timiService: TimiServiceApi,
    private val userPreference: UserPreference
): OAuthRepository {
    override suspend fun refreshUserToken() {
        timiService.refreshUserToken(
            userPreference.refreshToken
        )
    }
}