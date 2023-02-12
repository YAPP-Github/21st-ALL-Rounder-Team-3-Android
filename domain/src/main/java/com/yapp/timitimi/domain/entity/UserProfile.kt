package com.yapp.timitimi.domain.entity

data class UserProfile(
    val email: String,
    val nickname: String,
    val imageUrl: String
) {
    companion object {
        fun empty(): UserProfile {
            return UserProfile(
                email = "",
                nickname = "",
                imageUrl = ""
            )
        }
    }
}