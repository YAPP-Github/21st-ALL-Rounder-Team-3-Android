package com.yapp.timitimi.domain.entity

data class Project(
    val id: Int,
    val name: String,
    val startDate: String,
    val dueDate: String,
    val goal: String,
    val projectStatus: String,
    val progress: String,
    val participantInfos: List<Participant>,
    val projectThumbnailUrl: String,
    val teamThumbnailUrl: String,
    val myParticipantId: Int,
    val dday: Int,
) {
    companion object {
        fun empty(): Project {
            return Project(
                id = 0,
                name = "",
                startDate = "",
                dueDate = "",
                goal = "",
                projectStatus = "",
                progress = "",
                participantInfos = emptyList(),
                projectThumbnailUrl = "",
                teamThumbnailUrl = "",
                myParticipantId = 0,
                dday = 0,
            )
        }
    }
}
