package com.yapp.timitimi.data.base

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val status: Boolean,
    val message: String?,
    val data: T,
)