package com.yapp.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val LightColors = lightColors(
    primary = Purple700,
    primaryVariant = Purple100,
    secondary = Green600,
    secondaryVariant = Green100
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
        typography = typography
    ) {
        CompositionLocalProvider(
            LocalRippleTheme provides PrimaryRippleTheme,
            content = content
        )
    }
}

@Immutable
private object PrimaryRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = MaterialTheme.colors.primary,
        lightTheme = MaterialTheme.colors.isLight
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        contentColor = MaterialTheme.colors.primary,
        lightTheme = MaterialTheme.colors.isLight
    )
}