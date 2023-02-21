package com.yapp.timitimi.presentation.ui.projectlist.model

import com.yapp.timitimi.domain.entity.Participant
import com.yapp.timitimi.domain.entity.Project

data class ProjectListItem(
    val id: Int,
    val dday: String,
    val background: String,
    val title: String,
    val dueDate: String,
    val participant: List<Participant>
)

fun Project.toAllProjectItem(): ProjectListItem {
    return ProjectListItem(
        id = this.id,
        dday = convertdDay(startDate, dueDate),
        dueDate = convertDueDate(startDate, dueDate),
        background = this.projectThumbnailUrl,
        title = this.goal,
        participant = this.participantInfos
    )
}

private fun convertdDay(startDate: String, endDate: String): String {
    return "D-10"
}

private fun convertDueDate(startDate: String, endDate: String): String {
    return "$startDate ~ $endDate"
}