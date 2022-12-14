package com.yapp.timitimi.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val LightColors = lightColors(
    primary = Purple500,
    primaryVariant = Purple100,
    secondary = Green500,
    secondaryVariant = Green100
)

@Composable
fun AllRounder3Theme(
    content: @Composable () -> Unit,
) {
    val colors = LightColors
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(
            color = Color.White
        )
    }
    MaterialTheme(
        colors = colors,
        content = content
    )
}