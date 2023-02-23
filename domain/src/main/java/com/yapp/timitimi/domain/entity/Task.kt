package com.yapp.timitimi.domain.entity

data class Task(
    val confirmCount: Int,
    val dueDate: String,
    val feedbackDueDate: String,
    val feedbackRequiredPersonnel: Int,
    val id: Int,
    val memo: String,
    val representative: Representative,
    val startDate: String,
    val taskContents: List<TaskContent>,
    val taskStatus: String,
    val title: String
)

data class TaskContent(
    val taskContentId: Int,
    val title: String,
    val url: String
)

data class Representative(
    val imageUrl: String,
    val name: String,
    val participantId: Int
)
