package com.yapp.designsystem.component


import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.yapp.designsystem.R
import com.yapp.designsystem.border.TimiBorder
import com.yapp.designsystem.modifier.timiClipBorder
import com.yapp.designsystem.row.CenterVerticallyRow
import com.yapp.designsystem.theme.Green500
import com.yapp.designsystem.theme.Purple500

val BoxSpace = 14.dp

@Composable
fun TimiProjectCard(
    modifier: Modifier = Modifier,
    @DrawableRes backgroundImageId: Int,
    isLeader: Boolean,
    title: String,
    difficulty: String,
    goal: String,
    dDay: String,
    period: String,
    progress: Float,
    leaderImage: String,
) {
    val (color, cardHeight) = if (isLeader) {
        Green500 to 188.dp
    } else {
        Purple500 to 168.dp
    }
    Box(
        modifier = modifier
            .height(cardHeight)
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(16.dp)
            )
            .paint(
                painter = painterResource(id = backgroundImageId),
                contentScale = ContentScale.Crop,
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            if (isLeader) {
                TimiCaption2SemiBold(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = stringResource(id = R.string.leader),
                    color = color
                )
            }
            TimiH3SemiBold(
                text = title,
                color = Color.White,
            )
            CenterVerticallyRow(
                modifier = Modifier.padding(top = 16.dp),
                horizontalSpace = 8.dp,
            ) {
                TimiMediumRoundedBadge(
                    text = difficulty,
                    border = TimiBorder(
                        color = color,
                    ),
                    fontColor = color,
                )
                TimiCaption2Regular(
                    text = goal,
                    color = Color.White,
                )
            }
            Row(
                modifier = Modifier.padding(top = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(space = 33.dp),
            ) {
                CenterVerticallyRow(
                    horizontalSpace = 8.dp,
                ) {
                    TimiMediumRoundedBadge(
                        text = dDay,
                        border = null,
                        backgroundColor = color,
                        fontColor = Color.White,
                    )
                    TimiCaption2Regular(
                        text = period,
                        color = Color.White,
                    )
                }
                CenterVerticallyRow(
                    horizontalSpace = 8.dp
                ) {
                    TimiCaption2Regular(
                        text = "${(progress * 100).toInt()}%",
                        color = Color.White,
                    )
                    TimiLinearProgressBar(
                        strokeWidth = 6.dp,
                        progress = progress,
                        progressColor = color,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 13.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                BoxOverBox(
                    image = leaderImage,
                    count = 5,
                    overBoxColor = color,
                )
            }
        }
    }
}

@Composable
private fun BoxOverBox(
    modifier: Modifier = Modifier,
    image: Any?,
    count: Int,
    overBoxColor: Color,
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier.boxBorder()
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = image,
                contentDescription = "Profile Box",
            )
        }
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = BoxSpace
                            .toPx()
                            .toInt(),
                        y = 0
                    )
                }
                .boxBorder()
                .zIndex(1f)
                .background(overBoxColor),
            contentAlignment = Alignment.Center,
        ) {
            TimiCaption3Regular(
                text = "+$count",
                color = Color.White
            )
        }
    }
}

private fun Modifier.boxBorder(): Modifier = then(
    other = Modifier
        .timiClipBorder(
            border = TimiBorder(
                width = 1.dp,
                color = Color.White,
            ),
            shape = CircleShape
        )
        .size(20.dp)
)

@Preview
@Composable
fun ProjectBarPreview() {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TimiProjectCard(
            backgroundImageId = R.drawable.dummy, //TODO(EvergreenTree97): 더미 백그라운드 이미지로, 나중에 삭제되어야 함
            isLeader = false,
            title = "고전문학사 팀플 3조",
            difficulty = "여유 있게",
            goal = "가보자고~",
            dDay = "D-10",
            period = "11/16 ~ 11/27",
            progress = 0.45f,
            leaderImage = "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        )
        TimiProjectCard(
            backgroundImageId = R.drawable.dummy,
            isLeader = true,
            title = "고전문학사 팀플 3조",
            difficulty = "여유 있게",
            goal = "가보자고~",
            dDay = "D-10",
            period = "11/16 ~ 11/27",
            progress = 0.45f,
            leaderImage = "https://cdn.pixabay.com/photo/2013/03/20/23/20/butterfly-95364_1280.jpg",
        )
    }
}