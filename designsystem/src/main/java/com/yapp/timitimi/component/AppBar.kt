package com.yapp.timitimi.component

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yapp.timitimi.designsystem.R
import com.yapp.timitimi.modifier.timiClickable

@Composable
fun TimiTopAppBar(
    firstTrailingIcon: (@Composable () -> Unit)? = null,
    secondTrailingIcon: (@Composable () -> Unit)? = null,
    thirdTrailingIcon: (@Composable () -> Unit)? = null,
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
                    .timiClickable(
                        onClick = onClickBackButton,
                        bounded = false,
                    ),
                painter = painterResource(id = R.drawable.icon_arrow_left),
                contentDescription = "timi top bar"
            )

            TimiH3SemiBold(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 10.dp),
                text = title,
                textAlign = if (isTextCenterAlignment) TextAlign.Center else TextAlign.Start,
                color = Color.Black,
            )

            firstTrailingIcon?.let { it() }
            secondTrailingIcon?.let { it() }
            thirdTrailingIcon?.let { it() }
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
            isTextCenterAlignment = true,
            firstTrailingIcon = { TopBarNotificationIcon(count = 1) {} },
            secondTrailingIcon = { TopBarEditIcon {} },
            thirdTrailingIcon = { TopBarDeleteIcon {} },
            title = "고전문학사 팀플 3조"
        )
    }
}
