package com.yapp.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val LightColors = lightColors(
    primary = Purple700,
    primaryVariant = Color.White,
    secondary = Purple100,
    secondaryVariant = Color.White
)

@Composable
fun AllRounder3Theme(
    content: @Composable () -> Unit,
) {
    val colors = LightColors
    val typography = Typography
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(
            color = Color.White
        )
    }
    MaterialTheme(
        colors = colors,
        typography = typography,
        content = content
    )
}