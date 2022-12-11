package com.yapp.presentation.ui.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.yapp.core.compose.drawColoredShadow
import com.yapp.presentation.R
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.CollapsingToolbarScope
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun MainScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val state = viewModel.viewState.collectAsState()

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.dispatch(MainIntent.CompleteLoading)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val rememberCollapsingToolbarState = rememberCollapsingToolbarScaffoldState()
    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = rememberCollapsingToolbarState,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            Header()
        }
    ) {
        Tasks(title = "나의 할일")
    }
}

@Composable
fun CollapsingToolbarScope.Header() {
    Card(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .drawColoredShadow(),
    ) {
        Column(
            Modifier.padding(horizontal = 16.dp),
        ) {
            TopAppBar(
                onClickLeftArrow = {},
                onClickNotification = {},
                hasNotification = true,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "2022-2 4차 산업 혁명의 이해 팀플",
                style = MaterialTheme.typography.h2,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(20.dp))
            BadgeString(
                title = "학기 성적 A+ 도전",
                badgeText = "여유 있게",
                borderColor = MaterialTheme.colors.primary,
                badgeColor = Color.White,
                badgeFontColor = MaterialTheme.colors.primary,
                space = 11.dp
            )
            Spacer(modifier = Modifier.height(12.dp))
            TaskBar(
                badgeText = "D-10",
                title = "11/16 ~ 12/7"
            )
            Spacer(modifier = Modifier.height(14.dp))
            MemberContents(
                title = "팀원 7명",
                memebers = dummyMembers
            )
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}


@Composable
fun TopAppBar(
    onClickLeftArrow: () -> Unit,
    onClickNotification: () -> Unit,
    hasNotification: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 40.dp)
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.clickable(
                    onClick = onClickLeftArrow
                ),
                painter = painterResource(id = R.drawable.left_arrow),
                contentDescription = "leftArrow"
            )
            Notification(
                hasNotification = hasNotification,
                onClick = onClickNotification,
            )
        }
    }

}


@Composable
fun TaskBar(
    title: String,
    badgeText: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 20.dp),
    ) {
        BadgeString(
            title = title,
            badgeText = badgeText,
            borderColor = Color.White,
            badgeColor = MaterialTheme.colors.primary,
            badgeFontColor = Color.White,
            space = 11.dp
        )
        LinearProgressBar(
            percent = 0.45f
        )
    }
}

data class Member(
    val profile: String,
    val name: String,
    val selected: Boolean
)

val dummyMembers = listOf(
    Member("https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg", "상록", true),
    Member("https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg", "세희", false),
    Member("https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg", "지율", true)
)

@Composable
fun MemberContents(
    title: String,
    memebers: List<Member>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 6.dp)
    ) {
        Text(
            text = title,
            color = Color.Black
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                MemberProfile(
                    profile = "",
                    name = "전체",
                    selected = true
                )
            }
            items(
                items = memebers,
                key = { it.name }
            ) { memeber ->
                MemberProfile(
                    profile = memeber.profile,
                    name = memeber.name,
                    selected = memeber.selected
                )
            }
        }
    }
}

@Composable
fun MemberProfile(
    profile: String,
    name: String,
    selected: Boolean,
) {
    val profileBorderColor by animateColorAsState(
        targetValue = if (selected) {
            Color.White
        } else {
            MaterialTheme.colors.primary
        }
    )
    val fontColor by animateColorAsState(
        targetValue = if (selected) {
            Color.Black
        } else {
            MaterialTheme.colors.primary
        }
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(size = 40.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = profileBorderColor,
                    shape = CircleShape
                ),
            model = profile,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = name,
            color = fontColor
        )
    }


}

@Composable
fun LinearProgressBar(
    percent: Float,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = (percent * 100).toInt().toString() + "%",
            style = MaterialTheme.typography.body1,
            color = Color.Black,
        )
        LinearProgressIndicator(
            modifier = Modifier.clip(shape = RoundedCornerShape(5.dp)),
            backgroundColor = MaterialTheme.colors.secondary,
            color = MaterialTheme.colors.primary,
            progress = percent,
        )
    }

}

@Composable
fun BadgeString(
    title: String,
    badgeText: String,
    borderColor: Color,
    badgeColor: Color,
    badgeFontColor: Color,
    space: Dp = 0.dp,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = space)
    ) {
        Badge(
            text = badgeText,
            borderColor = borderColor,
            backgroundColor = badgeColor,
            fontColor = badgeFontColor,
        )
        Text(
            text = title,
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
    }
}

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    text: String,
    borderColor: Color,
    backgroundColor: Color,
    fontColor: Color,
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp),
            )
            .clip(
                shape = RoundedCornerShape(8.dp),
            )
            .background(
                color = backgroundColor
            )
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = 2.dp,
                horizontal = 8.dp
            ),
            text = text,
            color = fontColor,
        )
    }
}

@Composable
fun Notification(
    hasNotification: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.clickable(
            onClick = onClick,
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.bell),
            contentDescription = "bell",
        )
        if (hasNotification) {
            DrawDot(
                dotSize = 5.dp,
                color = MaterialTheme.colors.primary,
            )
        }
    }

}

@Composable
fun DrawDot(
    dotSize: Dp,
    color: Color,
) {
    Canvas(
        modifier = Modifier.size(size = dotSize),
        onDraw = {
            val size = dotSize.toPx()
            drawCircle(
                color = color, radius = size / 2f
            )
        }
    )
}