package com.yapp.timitimi.data.response

import com.yapp.timitimi.domain.entity.Participant
import kotlinx.serialization.Serializable

@Serializable
data class ParticipantResponse(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val leader: Int,
)

internal fun ParticipantResponse.toDomain() = Participant(
    id = id,
    name = name,
    imageUrl = imageUrl,
    isLeader = leader == 1
)