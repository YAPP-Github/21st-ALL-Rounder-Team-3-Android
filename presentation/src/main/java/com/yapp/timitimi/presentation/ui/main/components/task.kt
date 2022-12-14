package com.yapp.timitimi.presentation.ui.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yapp.timitimi.component.TaskType
import com.yapp.timitimi.component.TimiBody2Medium
import com.yapp.timitimi.component.TimiTaskCard
import com.yapp.timitimi.theme.Gray600
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class TaskItem(
    val profile: String,
    val name: String,
    val startDate: String,
    val endDate: String,
    val title: String,
    val content: String,
)

@Composable
fun TaskContent(
    title: String,
    tasks: ImmutableList<TaskItem> = dummyTaskItems.toImmutableList()
) {
    val lazyListState = rememberLazyListState()
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
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
            items(
                items = tasks,
            ) { taskItem ->
                TimiTaskCard(
                    profile = taskItem.profile,
                    name = taskItem.name,
                    period = taskItem.startDate + taskItem.endDate,
                    badgeText = "?????? ?????? D-3",
                    content = taskItem.title,
                    subContent = taskItem.title,
                    taskType = TaskType.Progress,
                    isMe = false
                )
            }
        }
    }
}

val dummyTaskItems = listOf(
    TaskItem(
        profile = "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        name = "??????",
        startDate = "11.27",
        endDate = "11.29",
        title = "DBpia, RISS ?????? ?????????",
        content = "????????? ???????????? ?????????"
    ),
    TaskItem(
        profile = "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        name = "??????",
        startDate = "11.27",
        endDate = "11.29",
        title = "DBpia, RISS ?????? ?????????",
        content = "????????? ???????????? ?????????"
    ),
    TaskItem(
        profile = "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        name = "??????",
        startDate = "11.27",
        endDate = "11.29",
        title = "DBpia, RISS ?????? ?????????",
        content = "????????? ???????????? ?????????"
    ),
    TaskItem(
        profile = "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        name = "??????",
        startDate = "11.27",
        endDate = "11.29",
        title = "DBpia, RISS ?????? ?????????",
        content = "????????? ???????????? ?????????"
    ),
    TaskItem(
        profile = "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        name = "??????",
        startDate = "11.27",
        endDate = "11.29",
        title = "DBpia, RISS ?????? ?????????",
        content = "????????? ???????????? ?????????"
    )
)

@Composable
fun Task(
    taskItem: TaskItem
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .padding(vertical = 14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TaskContent(taskItem = taskItem)
            TaskProgress(
                progress = 0.45f,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
fun TaskContent(
    taskItem: TaskItem
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(size = 26.dp)
                    .clip(CircleShape),
                model = taskItem.profile,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = taskItem.name)
            Spacer(modifier = Modifier.width(12.dp))
            DuringDate(
                startDate = taskItem.startDate,
                endDate = taskItem.endDate,
                isDone = false
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = taskItem.title,
            style = MaterialTheme.typography.h4,
        )
        Spacer(modifier = Modifier.width(2.dp))
        /*Text(
            text = taskItem.title,
            style = Body3,
        )*/
    }
}

@Composable
fun DuringDate(
    startDate: String,
    endDate: String,
    isDone: Boolean,
) {
    val textColor = if (isDone) {
        MaterialTheme.colors.secondary
    } else {
        MaterialTheme.colors.primary
    }
    val aroundColor = if (isDone) {
        MaterialTheme.colors.secondaryVariant
    } else {
        MaterialTheme.colors.primaryVariant
    }
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = aroundColor
            )
            .background(
                color = aroundColor.copy(alpha = 0.5f)
            )
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = 2.dp,
                horizontal = 6.dp,
            ),
            text = buildAnnotatedString {
                append(text = startDate)
                append(text = " ~ ")
                append(text = endDate)
            },
            color = textColor
        )

    }
}

@Composable
fun TaskProgress(
    progress: Float,
    color: Color,
) {
    Box {
        CircularProgressIndicator(
            modifier = Modifier.size(size = 60.dp),
            color = color,
            progress = progress,
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "D-day",
            color = color,
            //style = Caption2,
        )
    }
}