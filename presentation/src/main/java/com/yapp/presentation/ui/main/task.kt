package com.yapp.presentation.ui.main

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
import com.yapp.presentation.R
import com.yapp.presentation.theme.Body3
import com.yapp.presentation.theme.Caption2

data class TaskItem(
    val profile: String,
    val name: String,
    val startDate: String,
    val endDate: String,
    val title: String,
    val content: String,
)

@Composable
fun Tasks(
    title: String
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 22.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = title)
            Icon(
                painter = painterResource(id = R.drawable.down_arrow),
                contentDescription = "downArrow"
            )
        }
        LazyColumn(
            modifier = Modifier.padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                items = dummyTaskItems,
            ) { taskItem ->
                Task(taskItem)
            }
        }
    }
}

val dummyTaskItems = listOf(
    TaskItem(
        profile = "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        name = "상록",
        startDate = "11.27",
        endDate = "11.29",
        title = "DBpia, RISS 논문 리서치",
        content = "워드로 정리해서 넘기기"
    ),
    TaskItem(
        profile = "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        name = "상록",
        startDate = "11.27",
        endDate = "11.29",
        title = "DBpia, RISS 논문 리서치",
        content = "워드로 정리해서 넘기기"
    ),
    TaskItem(
        profile = "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        name = "상록",
        startDate = "11.27",
        endDate = "11.29",
        title = "DBpia, RISS 논문 리서치",
        content = "워드로 정리해서 넘기기"
    ),
    TaskItem(
        profile = "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        name = "상록",
        startDate = "11.27",
        endDate = "11.29",
        title = "DBpia, RISS 논문 리서치",
        content = "워드로 정리해서 넘기기"
    ),
    TaskItem(
        profile = "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        name = "상록",
        startDate = "11.27",
        endDate = "11.29",
        title = "DBpia, RISS 논문 리서치",
        content = "워드로 정리해서 넘기기"
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
        Text(
            text = taskItem.title,
            style = Body3,
        )
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
            style = Caption2,
        )
    }
}