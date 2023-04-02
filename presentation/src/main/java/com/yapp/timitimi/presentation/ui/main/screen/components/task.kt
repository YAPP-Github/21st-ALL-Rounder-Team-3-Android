package com.yapp.timitimi.presentation.ui.main.screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yapp.timitimi.component.TaskType
import com.yapp.timitimi.component.TimiBody2Medium
import com.yapp.timitimi.component.TimiTaskCard
import com.yapp.timitimi.kotlin.immutableFilter
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.presentation.ui.createproject.screen.Spacing
import com.yapp.timitimi.presentation.ui.main.redux.MainState
import com.yapp.timitimi.presentation.ui.main.redux.TaskItem
import com.yapp.timitimi.presentation.ui.main.screen.Me
import com.yapp.timitimi.theme.Gray600
import kotlinx.collections.immutable.ImmutableList

object TaskClassification {
    enum class Whole(val text: String) {
        My("내 업무"),
        Other("다른 팀원 업무"),
        Done("완료된 업무");
    }

    const val Progress = "진행 중인 업무"
}

@Composable
internal fun TaskSection(
    modifier: Modifier = Modifier,
    state: MainState,
    isProfileSelected: Boolean,
    onClickTask: (task: TaskItem, isMe: Boolean) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val myTasks by remember(state.members) {
        derivedStateOf {
            state.tasks.immutableFilter { it.member.name == Me }
        }
    }
    val otherTasks by remember(state.members) {
        derivedStateOf {
            state.tasks.immutableFilter { it.member.name != Me }
        }
    }
    val doneTasks by remember {
        derivedStateOf {
            state.tasks.immutableFilter { it.taskType is TaskType.Done }
        }
    }
    val progressInTasks by remember {
        derivedStateOf {
            state.tasks.immutableFilter {
                isProfileSelected &&
                        it.member.name == state.members[state.selectedProfileIndex].name
            }
        }
    }
    val isDropDownHides = remember {
        mutableStateListOf(
            elements = Array(
                size = if (isProfileSelected) {
                    1
                } else {
                    TaskClassification.Whole.values().size
                },
                init = { true }
            )
        )
    }
    LazyColumn(
        modifier = modifier,
        state = lazyListState,
    ) {
        if (isProfileSelected) {
            taskDropBox(
                title = TaskClassification.Progress,
                tasks = progressInTasks,
                isHide = isDropDownHides[0],
                onClick = {
                    isDropDownHides[0] = isDropDownHides[0].not()
                },
                onClickTask = onClickTask,
            )
        } else {
            TaskClassification.Whole.values().forEachIndexed { index, task ->
                taskDropBox(
                    title = task.text,
                    tasks = when (task) {
                        TaskClassification.Whole.My -> myTasks
                        TaskClassification.Whole.Other -> otherTasks
                        TaskClassification.Whole.Done -> doneTasks
                    },
                    isHide = isDropDownHides[index],
                    onClick = {
                        isDropDownHides[index] = isDropDownHides[index].not()
                    },
                    onClickTask = onClickTask,
                )
            }
        }

    }
}

internal fun LazyListScope.taskDropBox(
    title: String,
    tasks: ImmutableList<TaskItem>,
    onClickTask: (task: TaskItem, isMe: Boolean) -> Unit,
    onClick: () -> Unit,
    isHide: Boolean,
) {
    item {
        Column(
            Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .timiClickable(
                        onClick = onClick,
                        rippleEnabled = true,
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TimiBody2Medium(
                    text = title,
                    color = Gray600,
                )
                Icon(
                    painter = painterResource(id = com.yapp.timitimi.designsystem.R.drawable.icon_arrow_down),
                    contentDescription = "downArrow"
                )
            }
        }

    }
    itemsIndexed(
        items = tasks,
    ) { index, taskItem ->
        AnimatedVisibility(visible = isHide) {
            TimiTaskCard(
                profile = (taskItem.member.profile ?: "") as String,
                name = taskItem.member.name,
                period = "${taskItem.startDate} ~ ${taskItem.endDate}",
                badgeText = taskItem.dDay,
                content = taskItem.title,
                subContent = taskItem.title,
                taskType = taskItem.taskType,
                isMe = taskItem.member.name == Me,
                onClick = {
                    onClickTask(taskItem, taskItem.member.name == Me)
                }
            )
        }
        if (index != tasks.lastIndex) {
            Spacing(height = 10.dp)
        }
    }
    item {
        Spacing(height = 32.dp)
    }
}
