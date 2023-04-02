package com.yapp.timitimi.data.response

import com.yapp.timitimi.domain.entity.LoginInfo

data class LoginResponse(
    val appToken: String,
    val refreshToken: String
)

internal fun LoginResponse.toDomain() = LoginInfo(
    appToken = appToken,
    refreshToken = refreshToken,
)