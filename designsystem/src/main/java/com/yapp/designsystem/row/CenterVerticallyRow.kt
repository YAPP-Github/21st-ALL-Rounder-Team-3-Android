package com.yapp.designsystem.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun CenterVerticallyRow(
    modifier: Modifier = Modifier,
    horizontalSpace: Dp = 0.dp,
    content: @Composable (RowScope.() -> Unit),
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(space = horizontalSpace),
    verticalAlignment = Alignment.CenterVertically,
    content = content,
)