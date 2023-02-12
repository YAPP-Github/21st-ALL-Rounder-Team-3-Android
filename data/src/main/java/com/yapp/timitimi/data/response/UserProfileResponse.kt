package com.yapp.timitimi.data.response

import com.yapp.timitimi.domain.entity.UserProfile
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    val email: String,
    val nickname: String,
    val imageUrl: String
)

internal fun UserProfileResponse.toDomain() = UserProfile(
    email = email,
    nickname = nickname,
    imageUrl = imageUrl,
)