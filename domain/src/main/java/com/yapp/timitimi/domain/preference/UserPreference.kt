package com.yapp.timitimi.domain.preference

import kotlinx.coroutines.flow.Flow

interface UserPreference {
    var accessToken: String
    var refreshToken: String
    var lastViewedProjectId: String
    fun getIsFirstProject(): Flow<Boolean>
    suspend fun setIsFirstProject(isFirstProject: Boolean)
    fun clear()
}