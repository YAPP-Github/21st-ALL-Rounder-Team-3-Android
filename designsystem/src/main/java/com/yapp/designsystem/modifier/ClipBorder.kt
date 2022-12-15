package com.yapp.designsystem.modifier

import androidx.compose.foundation.border
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.yapp.designsystem.border.TimiBorder

@Stable
fun Modifier.timiClipBorder(
    border: TimiBorder? = null,
    shape: Shape = RectangleShape,
) = composed {
    if (border != null) {
        border(
            border = border.asBorderStroke(),
            shape = shape
        ).clip(shape = shape)
    } else {
        clip(shape = shape)
    }
}