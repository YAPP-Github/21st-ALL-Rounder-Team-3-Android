package com.yapp.timitimi.data.response

import kotlinx.serialization.Serializable

@Serializable
data class ProjectIdResponse(
    val projectId: Int,
    val createdAt: String,
)