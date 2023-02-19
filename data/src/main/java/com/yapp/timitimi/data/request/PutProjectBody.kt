package com.yapp.timitimi.data.request

import com.yapp.timitimi.domain.entity.EditProjectInfo
import kotlinx.serialization.Serializable

@Serializable
data class PutProjectBody(
    val name: String,
    val startDate: String,
    val dueDate: String,
    val goal: String,
)

internal fun EditProjectInfo.toData() = PutProjectBody(
    name = name,
    startDate = startDate,
    dueDate = dueDate,
    goal = goal,
)