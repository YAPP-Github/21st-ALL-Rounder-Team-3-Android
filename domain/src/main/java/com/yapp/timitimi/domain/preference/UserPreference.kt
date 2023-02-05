package com.yapp.timitimi.domain.preference

import kotlinx.coroutines.flow.Flow

interface UserPreference {
    var accessToken: String
    var refreshToken: String
    fun getIsFirstProject(): Flow<Boolean>
    suspend fun setIsFirstProject(isFirstProject: Boolean)
    fun clear()
}