package com.yapp.designsystem.border

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class TimiBorder(
    val width: Dp = 1.dp,
    val color: Color,
) {
    private val brush
        get() = SolidColor(color)

    @Stable
    fun asBorderStroke() = BorderStroke(
        width = width,
        brush = brush,
    )
}