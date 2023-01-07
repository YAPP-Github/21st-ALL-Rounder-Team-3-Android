package com.yapp.timitimi.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yapp.timitimi.designsystem.R
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.theme.Purple500

@Composable
fun TopBarNotificationIcon(
    count: Int,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable(
                onClick = onClick,
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = false)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_notification),
            contentDescription = "notification icon",
        )

        if (count > 0) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .padding(3.dp),
                contentAlignment = Alignment.TopEnd,
            ) {
                Text(
                    modifier = Modifier
                        .size(11.dp)
                        .background(
                            color = Purple500,
                            shape = CircleShape
                        ),
                    text = if (count > 9) "9+" else count.toString(),
                    color = White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 7.sp
                )
            }
        }
    }
}

@Composable
fun TopBarEditIcon(
    onClick: () -> Unit,
) {
    Icon(
        modifier = Modifier
            .padding(4.dp)
            .timiClickable(
                onClick = onClick,
                bounded = false,
            ),
        painter = painterResource(id = R.drawable.icon_edit),
        contentDescription = "edit icon",
    )
}


@Composable
fun TopBarDeleteIcon(
    onClick: () -> Unit,
) {
    Icon(
        modifier = Modifier
            .padding(4.dp)
            .timiClickable(
                onClick = onClick,
                bounded = false,
            ),
        painter = painterResource(id = R.drawable.icon_delete),
        contentDescription = "delete icon",
    )
}

@Preview
@Composable
fun TimiAppBarIconsPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        TimiTopAppBar(
            firstTrailingIcon = { TopBarNotificationIcon(count = 1) {} },
            secondTrailingIcon = { TopBarEditIcon {} },
            thirdTrailingIcon = { TopBarDeleteIcon {} },
            onClickBackButton = {},
            isTextCenterAlignment = false
        )
    }
}
