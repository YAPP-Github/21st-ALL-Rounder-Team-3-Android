package com.yapp.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yapp.designsystem.R
import com.yapp.designsystem.theme.H3

@Composable
fun TimiTopAppBar(
    rightIcons: @Composable () -> Unit,
    isTextCenterAlignment: Boolean,
    onClickBackButton: () -> Unit,
    title: String = ""
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 40.dp)
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        onClick = onClickBackButton,
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = false)
                    ),
                painter = painterResource(id = R.drawable.icon_arrow_left),
                contentDescription = "timi top bar"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 10.dp),
                overflow = TextOverflow.Ellipsis,
                style = H3,
                maxLines = 1,
                textAlign = if (isTextCenterAlignment) TextAlign.Center else TextAlign.Start,
                text = title,
                color = Color.Black,
            )

            rightIcons()
        }
    }
}


@Preview
@Composable
fun TimiAppBarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TimiTopAppBar(
            onClickBackButton = {},
            isTextCenterAlignment = false,
            rightIcons = {
                TopBarNotificationIcon(count = 1) {}
                TopBarEditIcon {}
                TopBarDeleteIcon {}
            },
        )
    }
}
