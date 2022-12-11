package com.yapp.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yapp.designsystem.theme.Gray4
import com.yapp.designsystem.theme.Gray5
import com.yapp.designsystem.theme.Purple700

enum class TimiButtonUiState {
    ABLE, DISABLE
}

@Composable
fun TimiPrimaryButton(
    text: String,
    buttonUiState: TimiButtonUiState,
    onClick: () -> Unit,
) {
    val (backgroundColor, textColor) = when (buttonUiState) {
        TimiButtonUiState.ABLE -> {
            Purple700 to Color.White
        }

        TimiButtonUiState.DISABLE -> {
            Gray4 to Gray5
        }
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp)),
        enabled = buttonUiState == TimiButtonUiState.ABLE,
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

@Composable
fun TimiSecondaryButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = Purple700,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        onClick = { onClick() }) {

        Text(
            text = text,
            color = Purple700
        )
    }
}


@Preview
@Composable
fun TimiButtonPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TimiPrimaryButton(
            text = "test",
            buttonUiState = TimiButtonUiState.ABLE,
        ) {

        }

        Spacer(modifier = Modifier.height(20.dp))
        TimiPrimaryButton(
            text = "test",
            buttonUiState = TimiButtonUiState.DISABLE,
        ) {

        }

        Spacer(modifier = Modifier.height(20.dp))
        TimiSecondaryButton(
            text = "test",
        ) {
        }
    }
}

