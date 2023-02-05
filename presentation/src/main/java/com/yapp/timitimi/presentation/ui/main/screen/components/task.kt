package com.yapp.timitimi.presentation.ui.main.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.yapp.timitimi.component.TaskType
import com.yapp.timitimi.component.TimiBody2Medium
import com.yapp.timitimi.component.TimiTaskCard
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.presentation.ui.main.redux.MainState
import com.yapp.timitimi.theme.Gray600
import kotlinx.collections.immutable.ImmutableList

fun taskContent(
    title: String,
    tasks: ImmutableList<MainState.Task>,
    isHide: Boolean,
    onClick: () -> Unit,
) {
    var isHide by remember{ mutableStateOf(false) }
    item {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .timiClickable(
                    onClick = onClick,
                    rippleEnabled = false
                ),
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
    if (isHide) {
        items(
            items = tasks,
        ) { taskItem ->
            TimiTaskCard(
                profile = taskItem.member.profile,
                name = taskItem.member.name,
                period = taskItem.startDate + taskItem.endDate,
                badgeText = "확인 요청 D-3",
                content = taskItem.title,
                subContent = taskItem.title,
                taskType = TaskType.Progress,
                isMe = false
            )
        }
    }
}
