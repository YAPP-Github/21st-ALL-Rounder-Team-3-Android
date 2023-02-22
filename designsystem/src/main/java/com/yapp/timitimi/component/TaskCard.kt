package com.yapp.timitimi.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yapp.timitimi.designsystem.R
import com.yapp.timitimi.border.TimiBorder
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.row.CenterVerticallyRow
import com.yapp.timitimi.theme.Gray300
import com.yapp.timitimi.theme.Gray400
import com.yapp.timitimi.theme.Gray600
import com.yapp.timitimi.theme.Gray700
import com.yapp.timitimi.theme.Green100
import com.yapp.timitimi.theme.Green500
import com.yapp.timitimi.theme.Purple100
import com.yapp.timitimi.theme.Purple500

@Composable
fun TimiTaskCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    profile: String,
    name: String,
    period: String,
    badgeText: String,
    content: String,
    subContent: String,
    taskType: TaskType,
    isMe: Boolean,
) {
    val highLightColor = when (taskType) {
        is TaskType.Request -> Purple500
        else -> Color.White
    }
    val (mainColor, backgroundColor, borderColor) = when (taskType) {
        is TaskType.Request -> {
            listOf(Purple500, Purple100.copy(alpha = 0.5f), Purple100)
        }

        TaskType.Progress -> {
            listOf(Green500, Green100.copy(alpha = 0.5f), Green100)
        }

        TaskType.NotStarted -> {
            listOf(Gray600, Gray300.copy(alpha = 0.5f), Gray300)
        }

        else -> {
            listOf(Gray400, Gray300.copy(alpha = 0.5f), Gray300)
        }
    }
    val density = LocalDensity.current
    var boxHeight by remember { mutableStateOf(0.dp) }

    Row(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    bottomStart = 16.dp,
                )
            )
            .timiClickable(
                onClick = onClick,
            )
    ) {
        Box(
            modifier = Modifier
                .width(8.dp)
                .height(boxHeight + 32.dp)
                .background(highLightColor)
        )
        Column(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        topEnd = 16.dp,
                        bottomEnd = 16.dp,
                    )
                )
                .background(Color.White)
                .padding(vertical = 16.dp)
                .padding(start = 16.dp, end = 20.dp)
                .onSizeChanged {
                    with(density) {
                        boxHeight = it.height.toDp()
                    }
                },
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TimiBody3Regular(
                    text = period,
                    color = Gray600,
                )
                TimiHalfRoundedCaption2Badge(
                    text = when(taskType){
                        TaskType.NotStarted -> "시작 전"
                        TaskType.Progress -> "진행 중"
                        is TaskType.Request -> "피드백 요청 $badgeText"
                        else -> "완료"
                    },
                    border = TimiBorder(
                        color = borderColor
                    ),
                    backgroundColor = backgroundColor,
                    fontColor = mainColor,
                )
            }
            TimiH3SemiBold(
                modifier = Modifier.padding(top = 8.dp),
                text = content,
                singleLine = true,
            )
            TimiBody3Regular(
                text = subContent,
                color = Gray600,
                singleLine = true,
            )
            if (isMe.not()) {
                Divider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = Gray300
                )
                BottomArea(
                    profile = profile,
                    name = name,
                    isMe = isMe,
                ) {
                    when (taskType) {
                        is TaskType.Request -> {
                            CurrentTask(
                                currentConfirmation = taskType.currentConfirmation,
                                totalConfirmation = taskType.totalConfirmation,
                            )
                        }

                        is TaskType.Done -> {
                            DoneTask(
                                completedTask = taskType.completedTask,
                                notYetTask = taskType.notYetTask,
                                color = mainColor,
                            )
                        }

                        else -> {}
                    }
                }
            } else {
                when (taskType) {
                    is TaskType.Request -> {
                        Divider(
                            modifier = Modifier.padding(vertical = 10.dp),
                            color = Gray300
                        )
                        BottomArea(
                            profile = profile,
                            name = name,
                            isMe = isMe,
                        ) {
                            CurrentTask(
                                currentConfirmation = taskType.currentConfirmation,
                                totalConfirmation = taskType.totalConfirmation,
                            )
                        }
                    }

                    is TaskType.Done -> {
                        Divider(
                            modifier = Modifier.padding(vertical = 10.dp),
                            color = Gray300
                        )
                        BottomArea(
                            profile = profile,
                            name = name,
                            isMe = isMe,
                        ) {
                            DoneTask(
                                completedTask = taskType.completedTask,
                                notYetTask = taskType.notYetTask,
                                color = mainColor,
                            )
                        }
                    }

                    else -> {}
                }

            }
        }
    }
}

@Composable
fun BottomArea(
    modifier: Modifier = Modifier,
    profile: String,
    name: String,
    isMe: Boolean,
    rightComponent: (@Composable () -> Unit)? = null,
) {
    val arrangement = if (isMe) {
        Arrangement.End
    } else {
        Arrangement.SpaceBetween
    }
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isMe.not()) {
            ProfileRow(
                profile = profile,
                name = name,
            )
        }
        rightComponent?.invoke()
    }
}

@Composable
private fun CurrentTask(
    currentConfirmation: Int,
    totalConfirmation: Int,
) {
    CenterVerticallyRow(horizontalSpace = 6.dp) {
        TimiCaption2SemiBold(
            text = stringResource(id = R.string.confirmation_status),
            color = Gray600,
        )
        TimiCaption2SemiBold(
            text = "$currentConfirmation/$totalConfirmation",
            color = Gray600,
        )
    }
}


@Composable
private fun ProfileRow(
    profile: String,
    name: String,
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .size(size = 20.dp)
                .clip(CircleShape),
            model = profile,
            contentScale = ContentScale.Crop,
            contentDescription = "Task Profile"
        )
        TimiCaption2SemiBold(
            modifier = Modifier.padding(
                start = 6.dp,
                end = 12.dp
            ),
            text = name,
            color = Gray700
        )
    }
}

@Composable
private fun DoneTask(
    completedTask: Int,
    notYetTask: Int,
    color: Color,
) {
    CenterVerticallyRow(horizontalSpace = 10.dp) {
        CenterVerticallyRow(horizontalSpace = 4.dp) {
            Icon(
                painter = painterResource(id = R.drawable.icon_check_contained),
                tint = color,
                contentDescription = "check contained icon"
            )
            TimiCaption1Regular(
                text = completedTask.toString(),
                color = color,
            )
        }
        CenterVerticallyRow(horizontalSpace = 4.dp) {
            Icon(
                painter = painterResource(id = R.drawable.icon_alert_circle),
                tint = color,
                contentDescription = "alert circle icon"
            )
            TimiCaption1Regular(
                text = notYetTask.toString(),
                color = color,
            )
        }
    }
}


sealed class TaskType {
    object NotStarted : TaskType()
    object Progress : TaskType()
    data class Request(
        val confirmationPeriod: Int,
        val currentConfirmation: Int,
        val totalConfirmation: Int,
    ) : TaskType()

    data class Done(
        val completedTask: Int,
        val notYetTask: Int,
    ) : TaskType()
}

@Preview
@Composable
fun CardPreview() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TimiTaskCard(
            profile = "https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2076&q=80",
            name = "가연",
            period = "12.2 ~ 12.7",
            content = "참고문헌 검토하기",
            subContent = "표기법 준수할 것",
            badgeText = "D-13",
            taskType = TaskType.NotStarted,
            isMe = true,
            onClick = {},
        )
        TimiTaskCard(
            profile = "https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2076&q=80",
            name = "가연",
            period = "12.2 ~ 12.7",
            content = "참고문헌 검토하기",
            subContent = "표기법 준수할 것",
            badgeText = "D-13",
            taskType = TaskType.Progress,
            isMe = true,
            onClick = {},
        )

        TimiTaskCard(
            profile = "https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2076&q=80",
            name = "가연",
            period = "12.2 ~ 12.7",
            content = "참고문헌 검토하기",
            subContent = "표기법 준수할 것",
            badgeText = "D-13",
            taskType = TaskType.Request(
                confirmationPeriod = 3,
                currentConfirmation = 5,
                totalConfirmation = 6,
            ),
            isMe = true,
            onClick = {},
        )
        TimiTaskCard(
            profile = "https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2076&q=80",
            name = "가연",
            period = "12.2 ~ 12.7",
            content = "참고문헌 검토하기",
            subContent = "표기법 준수할 것",
            badgeText = "D-13",
            taskType = TaskType.Done(
                completedTask = 4,
                notYetTask = 2,
            ),
            isMe = true,
            onClick = {},
        )
        TimiTaskCard(
            profile = "https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2076&q=80",
            name = "가연",
            period = "12.2 ~ 12.7",
            content = "참고문헌 검토하기",
            subContent = "표기법 준수할 것",
            badgeText = "D-13",
            taskType = TaskType.NotStarted,
            isMe = false,
            onClick = {},
        )
        TimiTaskCard(
            profile = "https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2076&q=80",
            name = "가연",
            period = "12.2 ~ 12.7",
            content = "참고문헌 검토하기",
            subContent = "표기법 준수할 것",
            badgeText = "D-13",
            taskType = TaskType.Progress,
            isMe = false,
            onClick = {},
        )

        TimiTaskCard(
            profile = "https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2076&q=80",
            name = "가연",
            period = "12.2 ~ 12.7",
            content = "참고문헌 검토하기",
            subContent = "표기법 준수할 것",
            badgeText = "D-13",
            taskType = TaskType.Request(
                confirmationPeriod = 3,
                currentConfirmation = 5,
                totalConfirmation = 6,
            ),
            isMe = false,
            onClick = {},
        )
        TimiTaskCard(
            profile = "https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2076&q=80",
            name = "가연",
            period = "12.2 ~ 12.7",
            content = "참고문헌 검토하기sdsdsdsdsdsdsdsdsdsdsdsds",
            subContent = "표기법 준수할 것sdsdsddsdsdsddsdsdsdssdsdsdsddsd",
            badgeText = "D-13",
            taskType = TaskType.Done(
                completedTask = 4,
                notYetTask = 2,
            ),
            isMe = false,
            onClick = {},
        )
    }
}