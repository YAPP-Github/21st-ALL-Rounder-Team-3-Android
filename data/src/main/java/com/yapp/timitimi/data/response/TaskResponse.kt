package com.yapp.timitimi.data.response

import com.yapp.timitimi.domain.entity.Representative
import com.yapp.timitimi.domain.entity.Task
import com.yapp.timitimi.domain.entity.TaskContent
import com.yapp.timitimi.domain.entity.TaskStatus
import kotlinx.serialization.SerialInfo
import kotlinx.serialization.Serializable

@Serializable
data class TaskResponse(
    val confirmCount: Int,
    val dueDate: String,
    val feedbackDueDate: String = "2022-02-02",
    val feedbackRequiredPersonnel: Int,
    val id: Int,
    val memo: String,
    val representative: RepresentativeResponse,
    val startDate: String,
    val taskContents: List<TaskContentResponse>,
    val taskStatus: String = "BEFORE",
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
    taskStatus = TaskStatus.valueOf(taskStatus),
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