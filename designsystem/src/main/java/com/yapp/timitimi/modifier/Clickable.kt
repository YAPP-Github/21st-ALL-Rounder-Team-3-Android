package com.yapp.timitimi.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.timiClickable(
    onClick: (() -> Unit)?,
    rippleEnabled: Boolean = true,
    bounded: Boolean = true,
) = if (onClick != null) {
    composed {
        clickable(
            onClick = onClick,
            indication = rememberRipple(bounded = bounded).takeIf { rippleEnabled },
            interactionSource = remember { MutableInteractionSource() }
        )
    }
} else {
    this
}
