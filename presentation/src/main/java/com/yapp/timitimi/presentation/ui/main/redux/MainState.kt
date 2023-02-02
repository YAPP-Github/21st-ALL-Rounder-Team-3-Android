package com.yapp.timitimi.presentation.ui.main.redux

import com.yapp.timitimi.redux.BaseState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class MainState(
    val notificationCount: Int = 0,
    val project: Project = Project(),
    val members: ImmutableList<Member> = dummyMembers,
    val selectedProfileIndex: Int = 0,
    val isDropDownExpanded: ImmutableList<Boolean> = persistentListOf(),
    val tasks: ImmutableList<Task> = persistentListOf()
) : BaseState {
    data class Project(
        val name: String = "",
        val startDate: String = "",
        val endDate: String = "",
        val dDay: Int = 0,
        val progress: Float = 0f,
        val memberCount: Int = 0,
    )

    data class Task(
        val memeber: Member = Member(),
        val startDate: String = "",
        val endDate: String = "",
        val title: String = "",
        val memo: String = "",
        val completionCount: Int = 0,
        val totalCount: Int = 0,
    )

    data class Member(
        val isLeader: Boolean = false,
        val profile: String = "",
        val name: String = "",
    )
}


val dummyMembers = persistentListOf(
    MainState.Member(
        true,
        "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        "상록"
    ),
    MainState.Member(
        false,
        "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        "세희"
    ),
    MainState.Member(
        false,
        "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        "정현"
    ),
)