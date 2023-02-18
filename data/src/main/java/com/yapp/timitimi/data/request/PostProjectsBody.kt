package com.yapp.timitimi.data.request

import com.yapp.timitimi.domain.entity.CreateProjectsInfo
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

internal fun CreateProjectsInfo.toData() = PostProjectsBody(
    name = name,
    startDate = startDate,
    dueDate = dueDate,
    goal = goal,
    difficulty = difficulty,
    projectStatus = projectStatus,
)