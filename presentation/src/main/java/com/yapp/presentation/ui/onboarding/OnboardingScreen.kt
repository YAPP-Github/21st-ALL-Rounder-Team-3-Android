package com.yapp.presentation.ui.onboarding

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yapp.core.ui.startActivityWithAnimation
import com.yapp.designsystem.component.TimiBody2Medium
import com.yapp.designsystem.component.TimiButton1SemiBold
import com.yapp.designsystem.component.TimiH1SemiBold
import com.yapp.designsystem.theme.Purple500
import com.yapp.presentation.R
import com.yapp.presentation.ui.createproject.CreateProjectActivity

@Composable
fun OnboardingScreen(
    context: Context = LocalContext.current
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimiH1SemiBold(
            modifier = Modifier.padding(bottom = 30.dp),
            text = stringResource(id = R.string.onboarding_project_title),
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        TimiBody2Medium(
            modifier = Modifier.padding(
                start = 40.dp,
                end = 40.dp,
                bottom = 100.dp
            ),
            text = stringResource(id = R.string.onboarding_project_description),
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        LargeButton(
            text = stringResource(id = R.string.onboarding_project_start_button),
            backgroundColor = Purple500,
            enabled = true,
        ) {
            (context as Activity).startActivityWithAnimation<CreateProjectActivity>()
        }

        TimiBody2Medium(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.onboarding_project_help_description),
            textAlign = TextAlign.Center,
            color = Purple500
        )
    }
}

@Composable
fun LargeButton(
    text: String,
    enabled: Boolean,
    textColor: Color = Color.White,
    backgroundColor: Color = Purple500,
    strokeColor: Color = Purple500,
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

        TimiButton1SemiBold(
            text = text,
            color = textColor
        )
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen()
}
