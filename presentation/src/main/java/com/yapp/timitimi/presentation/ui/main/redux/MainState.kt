package com.yapp.timitimi.presentation.ui.main.redux

import androidx.compose.ui.graphics.painter.Painter
import com.yapp.timitimi.component.TaskType
import com.yapp.timitimi.domain.entity.Project
import com.yapp.timitimi.presentation.ui.main.screen.Me
import com.yapp.timitimi.redux.BaseState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class MainState(
    val currentStep: ScreenStep = ScreenStep.First,
    val notificationCount: Int = 0,
    val isFirstProject: Boolean = true,
    val project: ProjectItem = ProjectItem(),
    val members: ImmutableList<Member> = persistentListOf(),
    val selectedProfileIndex: Int = 0,
    val tasks: ImmutableList<Task> = persistentListOf(),
) : BaseState {

    data class Task(
        val taskType: TaskType = TaskType.NotStarted,
        val id: Int,
        val member: Member = Member(),
        val startDate: String = "",
        val endDate: String = "",
        val dDay: String = "",
        val title: String = "",
        val memo: String = "",
        val completionCount: Int = 0,
        val totalCount: Int = 0,
    )
}

data class ProjectItem(
    val id: Int = 1,
    val name: String = "고전문학사 팀플 3조",
    val memo: String = "학기 성적 A+ 도전 도전 도전 도전🎃",
    val startDate: String = "11.16",
    val endDate: String = "12.7",
    val dDay: String = "D-10",
    val memberCount: Int = 0,
)

fun Project.toPresentationModel() = ProjectItem(
    id = id,
    name = name,
    memo = goal,
    startDate = startDate,
    endDate = dueDate,
    dDay = when {
        dday == 0 -> "D-Day"
        dday > 0 -> "D-$dday"
        else -> "완료"
    },
)

data class Member(
    val id: Int = 0,
    val isLeader: Boolean = false,
    val profile: Any? = null,
    val name: String = "",
)

enum class ScreenStep(private val index: Int) {
    First(0),
    Second(1),
    Main(2);

    operator fun plus(next: Int): ScreenStep = values().first { it.index == index + next }
}
