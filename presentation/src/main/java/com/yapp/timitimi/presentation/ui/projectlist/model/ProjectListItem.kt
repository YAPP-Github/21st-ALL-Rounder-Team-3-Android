package com.yapp.timitimi.presentation.ui.projectlist.model

import com.yapp.timitimi.domain.entity.Participant
import com.yapp.timitimi.domain.entity.Project
import kotlin.random.Random

data class ProjectListItem(
    val id: Int,
    val dday: String,
    val background: String,
    val title: String,
    val dueDate: String,
    val participant: List<Participant>
)

fun Project.toAllProjectItem(index: Int): ProjectListItem {
    // to add mock data
    val participants = convertMockParticipantsData(index, participantInfos)

    return ProjectListItem(
        id = id,
        dday = convertdDay(startDate, dueDate),
        dueDate = convertDueDate(startDate, dueDate),
        background = projectThumbnailUrl,
        title = goal,
        participant = participants
    )
}

private fun convertMockParticipantsData(index: Int, participantInfos: List<Participant>): List<Participant> {
    return if (index == 0 || index == 2) {
        participantInfos.toMutableList().apply {
            add(
                Participant(
                    id = 212,
                    name = "김티미",
                    imageUrl = "https://picsum.photos/200",
                    isLeader = false
                )
            )
            add(
                Participant(
                    id = 213,
                    name = "홍티미",
                    imageUrl = "https://picsum.photos/200",
                    isLeader = false
                )
            )
            add(
                Participant(
                    id = 214,
                    name = "박팀장",
                    imageUrl = "https://picsum.photos/200",
                    isLeader = false
                )
            )
            add(
                Participant(
                    id = 214,
                    name = "박팀장",
                    imageUrl = "https://picsum.photos/200",
                    isLeader = false
                )
            )
            add(
                Participant(
                    id = 214,
                    name = "박팀장",
                    imageUrl = "https://picsum.photos/200",
                    isLeader = false
                )
            )
            add(
                Participant(
                    id = 214,
                    name = "박팀장",
                    imageUrl = "https://picsum.photos/200",
                    isLeader = false
                )
            )
        }
    } else participantInfos
}

private fun convertdDay(startDate: String, endDate: String): String {
    val date = 15
    val randomInt = Random.nextInt(10)
    return "D-${date - randomInt}"
}

private fun convertDueDate(startDate: String, endDate: String): String {
    return "$startDate ~ $endDate"
}