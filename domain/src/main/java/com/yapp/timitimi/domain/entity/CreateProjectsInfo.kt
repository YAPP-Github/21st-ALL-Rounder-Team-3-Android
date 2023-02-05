package com.yapp.timitimi.domain.entity

data class CreateProjectsInfo(
    val name: String,
    val startDate: String,
    val dueDate: String,
    val goal: String,
    val difficulty: Int,
    val projectStatus: String
)