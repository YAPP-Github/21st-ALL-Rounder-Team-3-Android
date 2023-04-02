package com.yapp.timitimi.data.request

import com.yapp.timitimi.domain.entity.LoginProviderInfo
import kotlinx.serialization.Serializable

@Serializable
data class PostLoginBody(
    val accessToken: String,
    val provider: String
)

internal fun LoginProviderInfo.toData() = PostLoginBody(
    accessToken = appToken,
    provider = provider
)
