package com.yapp.timitimi.domain.respository

interface OAuthRepository {
    suspend fun refreshUserToken()
}