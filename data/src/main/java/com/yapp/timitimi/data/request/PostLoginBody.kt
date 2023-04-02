package com.yapp.timitimi.data.request

import com.yapp.timitimi.domain.entity.LoginProviderInfo

data class PostLoginBody(
    val appToken: String,
    val provider: String
)

internal fun LoginProviderInfo.toData() = PostLoginBody(
    appToken = appToken,
    provider = provider
)
