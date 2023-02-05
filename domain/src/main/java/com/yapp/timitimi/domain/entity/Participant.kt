package com.yapp.timitimi.domain.entity

data class Participant(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isLeader: Boolean,
)
