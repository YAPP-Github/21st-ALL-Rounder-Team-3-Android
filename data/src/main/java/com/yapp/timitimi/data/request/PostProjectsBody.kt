package com.yapp.timitimi.data.request

import kotlinx.serialization.Serializable

@Serializable
data class PostProjectsBody(
    val name: String,
    val startDate: String,
    val dueDate: String,
    val goal: String,
    val difficulty: Int,
    val projectStatus: String
)