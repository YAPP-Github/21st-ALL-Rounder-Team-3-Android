package com.yapp.timitimi.data.response

import com.yapp.timitimi.domain.entity.Representative
import com.yapp.timitimi.domain.entity.Task
import com.yapp.timitimi.domain.entity.TaskContent
import kotlinx.serialization.Serializable

@Serializable
data class TaskResponse(
    val confirmCount: Int,
    val dueDate: String,
    val feedbackDueDate: String,
    val feedbackRequiredPersonnel: Int,
    val id: Int,
    val memo: String,
    val representative: RepresentativeResponse,
    val startDate: String,
    val taskContents: List<TaskContentResponse>,
    val taskStatus: String,
    val title: String
)

@Serializable
data class TaskContentResponse(
    val taskContentId: Int,
    val title: String,
    val url: String
)

@Serializable
data class RepresentativeResponse(
    val imageUrl: String,
    val name: String,
    val participantId: Int
)

internal fun TaskResponse.toDomain() = Task(
    confirmCount = confirmCount,
    dueDate = dueDate,
    feedbackDueDate = feedbackDueDate,
    feedbackRequiredPersonnel = feedbackRequiredPersonnel,
    id = id,
    memo = memo,
    representative = representative.toDomain(),
    startDate = startDate,
    taskContents = taskContents.map(TaskContentResponse::toDomain),
    taskStatus = taskStatus,
    title = title
)

internal fun TaskContentResponse.toDomain() = TaskContent(
    taskContentId = taskContentId,
    title = title,
    url = url,
)

internal fun RepresentativeResponse.toDomain() = Representative(
    imageUrl = imageUrl,
    name = name,
    participantId = participantId,
)