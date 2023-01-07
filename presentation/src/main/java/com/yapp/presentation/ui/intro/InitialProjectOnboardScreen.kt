package com.yapp.presentation.ui.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yapp.presentation.R

//todo@jsh-me
@Composable
fun InitialProjectOnboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 30.dp),
            text = stringResource(id = R.string.initial_onboard_project_title),
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.padding(
                start = 40.dp,
                end = 40.dp,
                bottom = 100.dp
            ),
            text = "우리 서비스 쓰면 좋은 점 블라블라라 우리 서비스 쓰면 좋은 점 블라블라라 우리 서비스 쓰면 좋은 점 블라블라라",
            textAlign = TextAlign.Center
        )

        LargeButton(
            text = "프로젝트 시작하기",
            backgroundColor = Color.Magenta,
            enabled = true,
        ) {
        }
    }
}

@Composable
fun LargeButton(
    text: String,
    enabled: Boolean,
    textColor: Color = Color.White,
    backgroundColor: Color = Color.Transparent,
    strokeColor: Color = Color.Transparent,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(shape = RoundedCornerShape(16.dp), color = backgroundColor)
            .border(
                width = 1.dp,
                color = strokeColor,
                shape = RoundedCornerShape(16.dp)
            ),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        onClick = { onClick() }) {

        Text(
            text = text,
            color = textColor
        )
    }
}