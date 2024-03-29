package com.yapp.timitimi.data.response

import com.yapp.timitimi.domain.entity.Project
import kotlinx.serialization.Serializable

@Serializable
data class ProjectResponse(
    val id: Int,
    val name: String,
    val startDate: String,
    val dueDate: String,
    val goal: String,
    val difficulty: String,
    val projectStatus: String,
    val progress: String,
    val participantInfos: List<ParticipantResponse>,
    val projectThumbnailUrl: String,
    val teamThumbnailUrl: String,
    val myParticipantId: Int,
    val dday: Int,
)

internal fun ProjectResponse.toDomain() = Project(
    id = id,
    name = name,
    startDate = startDate,
    dueDate = dueDate,
    goal = goal,
    projectStatus = projectStatus,
    progress = progress,
    participantInfos = participantInfos.map {
        it.toDomain()
    },
    projectThumbnailUrl = projectThumbnailUrl,
    teamThumbnailUrl = teamThumbnailUrl,
    myParticipantId = myParticipantId,
    dday = dday,
)