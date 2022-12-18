package com.yapp.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yapp.designsystem.border.TimiBorder
import com.yapp.designsystem.modifier.timiClipBorder
import com.yapp.designsystem.theme.Gray100
import com.yapp.designsystem.theme.Gray200
import com.yapp.designsystem.theme.Gray500
import com.yapp.designsystem.theme.Green100
import com.yapp.designsystem.theme.Green500
import com.yapp.designsystem.theme.Purple100
import com.yapp.designsystem.theme.Purple500

@Composable
fun TimiMediumRoundedBadge(
    modifier: Modifier = Modifier,
    text: String,
    border: TimiBorder? = TimiBorder(
        color = Purple500,
    ),
    backgroundColor: Color = Color.White,
    fontColor: Color = Purple500,
) {
    TimiBasicBadge(
        modifier = modifier,
        text = text,
        shape = RoundedCornerShape(8.dp),
        fontColor = fontColor,
        border = border,
        backgroundColor = backgroundColor,
    )
}

@Composable
fun TimiSmallRoundedBadge(
    modifier: Modifier = Modifier,
    text: String,
    border: TimiBorder? = TimiBorder(
        color = Purple500,
    ),
    backgroundColor: Color = Color.White,
    fontColor: Color = Purple500,
) {
    TimiBasicBadge(
        modifier = modifier,
        text = text,
        shape = RoundedCornerShape(4.dp),
        fontColor = fontColor,
        border = border,
        backgroundColor = backgroundColor,
    )
}

@Composable
fun TimiBasicBadge(
    modifier: Modifier = Modifier,
    text: String,
    shape: Shape,
    fontColor: Color,
    border: TimiBorder?,
    backgroundColor: Color,
) {
    Box(
        modifier = modifier
            .timiClipBorder(
                border = border,
                shape = shape
            )
            .background(
                color = backgroundColor
            ),
    ) {
        TimiCaption1Regular(
            modifier = Modifier.padding(
                horizontal = 8.dp,
            ),
            text = text,
            color = fontColor
        )
    }
}


@Composable
@Preview
fun BadgePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TimiMediumRoundedBadge(text = "완벽하게")
        TimiMediumRoundedBadge(
            text = "D-10",
            border = null,
            backgroundColor = Purple500,
            fontColor = Color.White,
        )

        TimiSmallRoundedBadge(
            text = "12.2 ~ 12.7",
            border = TimiBorder(
                color = Purple100,
            ),
            backgroundColor = Purple100.copy(
                alpha = 0.6f
            ),
            fontColor = Purple500,
        )
        TimiSmallRoundedBadge(
            text = "12.2 ~ 12.7",
            border = TimiBorder(
                color = Green100
            ),
            backgroundColor = Green100.copy(
                alpha = 0.6f
            ),
            fontColor = Green500,
        )
        TimiSmallRoundedBadge(
            text = "12.2 ~ 12.7",
            border = TimiBorder(
                color = Gray200
            ),
            backgroundColor = Gray100,
            fontColor = Gray500,
        )
    }
}