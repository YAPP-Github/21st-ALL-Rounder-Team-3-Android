package com.yapp.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yapp.designsystem.R
import com.yapp.designsystem.border.TimiBorder
import com.yapp.designsystem.modifier.timiClipBorder
import com.yapp.designsystem.row.CenterVerticallyRow
import com.yapp.designsystem.theme.Gray200
import com.yapp.designsystem.theme.Gray300
import com.yapp.designsystem.theme.Gray500
import com.yapp.designsystem.theme.Gray600
import com.yapp.designsystem.theme.Gray700
import com.yapp.designsystem.theme.Green100
import com.yapp.designsystem.theme.Green500
import com.yapp.designsystem.theme.Purple100
import com.yapp.designsystem.theme.Purple500

@Composable
fun TimiTaskCard(
    modifier: Modifier = Modifier,
    profile: String,
    name: String,
    period: String,
    content: String,
    subContent: String,
    progress: Float,
    progressBarText: String,
    taskType: TaskType,
) {
    val (mainColor, backgroundColor, borderColor) = when (taskType) {
        TaskType.Progress -> {
            listOf(Purple500, Purple100.copy(alpha = 0.5f), Purple100)
        }
        is TaskType.Request -> {
            listOf(Green500, Green100.copy(alpha = 0.5f), Green100)
        }
        is TaskType.Done -> {
            listOf(Gray500, Gray300.copy(alpha = 0.5f), Gray300)
        }
    }
    Column(
        modifier = modifier
            .timiClipBorder(
                border = TimiBorder(color = Gray200),
                shape = RoundedCornerShape(16.dp)
            )
            .background(Color.White)
            .padding(top = 14.dp)
            .padding(horizontal = 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TaskContent(
                profile = profile,
                name = name,
                period = period,
                content = content,
                subContent = subContent,
                mainColor = mainColor,
                backgroundColor = backgroundColor,
                borderColor = borderColor,
            )
            TimiCircularProgressBar(
                centerText = progressBarText,
                progress = progress,
                color = mainColor,
                backgroundColor = backgroundColor,
            )
        }
        when (taskType) {
            TaskType.Progress -> {
                Spacer(modifier = Modifier.padding(top = 14.dp))
            }
            is TaskType.Request -> {
                Divider(
                    modifier = Modifier.padding(
                        top = 12.dp,
                        bottom = 8.dp
                    ),
                    color = Gray300
                )
                BottomRequestArea(
                    text = stringResource(
                        id = R.string.remain_check_period,
                        taskType.confirmationPeriod
                    ),
                    color = mainColor
                ) {
                    CurrentTask(
                        currentConfirmation = taskType.currentConfirmation,
                        totalConfirmation = taskType.totalConfirmation,
                        color = mainColor,
                    )
                }
            }
            is TaskType.Done -> {
                Divider(
                    modifier = Modifier.padding(
                        top = 12.dp,
                        bottom = 8.dp
                    ),
                    color = Gray300
                )
                BottomRequestArea(
                    text = stringResource(id = R.string.complete),
                    color = mainColor
                ) {
                    DoneTask(
                        completedTask = taskType.completedTask,
                        notYetTask = taskType.notYetTask,
                        color = mainColor,
                    )
                }
            }
        }
    }
}

@Composable
fun TaskContent(
    profile: String,
    name: String,
    period: String,
    content: String,
    subContent: String,
    mainColor: Color,
    backgroundColor: Color,
    borderColor: Color,
) {
    Column {
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(size = 26.dp)
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
            TimiSmallRoundedBadge(
                text = period,
                border = TimiBorder(
                    color = borderColor
                ),
                backgroundColor = backgroundColor,
                fontColor = mainColor,
            )
        }
        TimiH4SemiBold(
            modifier = Modifier.padding(bottom = 2.dp),
            text = content
        )
        TimiBody4Regular(
            text = subContent,
            color = Gray600,
        )
    }
}

@Composable
fun BottomRequestArea(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    rightComponent: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 11.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TimiCaption1SemiBold(
            text = text,
            color = color
        )
        rightComponent()
    }
}

@Composable
private fun CurrentTask(
    currentConfirmation: Int,
    totalConfirmation: Int,
    color: Color,
) {
    CenterVerticallyRow(horizontalSpace = 6.dp) {
        TimiCaption2SemiBold(text = stringResource(id = R.string.confirmation_status))
        TimiCaption2SemiBold(
            text = "$currentConfirmation/$totalConfirmation",
            color = color,
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        TimiTaskCard(
            profile = "https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2076&q=80",
            name = "가연",
            period = "12.2 ~ 12.7",
            content = "참고문헌 검토하기",
            subContent = "표기법 준수할 것",
            progress = 0.9f,
            progressBarText = "D-13",
            taskType = TaskType.Progress,
        )

        TimiTaskCard(
            profile = "https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2076&q=80",
            name = "가연",
            period = "12.2 ~ 12.7",
            content = "참고문헌 검토하기",
            subContent = "표기법 준수할 것",
            progress = 0.9f,
            progressBarText = "D-13",
            taskType = TaskType.Request(
                confirmationPeriod = 3,
                currentConfirmation = 5,
                totalConfirmation = 6,
            ),
        )
        TimiTaskCard(
            profile = "https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2076&q=80",
            name = "가연",
            period = "12.2 ~ 12.7",
            content = "참고문헌 검토하기",
            subContent = "표기법 준수할 것",
            progress = 0.9f,
            progressBarText = "D-13",
            taskType = TaskType.Done(
                completedTask = 4,
                notYetTask = 2,
            ),
        )
    }
}