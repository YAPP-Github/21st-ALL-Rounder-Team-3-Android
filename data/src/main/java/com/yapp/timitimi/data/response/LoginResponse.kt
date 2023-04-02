package com.yapp.timitimi.data.response

import com.yapp.timitimi.domain.entity.LoginInfo
import kotlinx.serialization.Serializable

@Serializable

data class LoginResponse(
    val appToken: String,
    val refreshToken: String
)

internal fun LoginResponse.toDomain() = LoginInfo(
    appToken = appToken,
    refreshToken = refreshToken,
)