package com.yapp.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.yapp.designsystem.theme.Purple100
import com.yapp.designsystem.theme.Purple500

private val CircularProgressBarStrokeWidth = 5.dp
private val CircularProgressBarSize = 60.dp

@Composable
fun TimiLinearProgressBar(
    modifier: Modifier = Modifier,
    strokeWidth: Dp,
    progress: Float,
    progressColor: Color = Purple500,
    backgroundColor: Color = Purple100,
) {
    Canvas(
        modifier = modifier
            .clip(CircleShape)
            .height(strokeWidth)
            .fillMaxWidth()
    ) {
        val strokeSize = size.height
        drawLinearIndicatorBackground(
            color = backgroundColor,
            strokeWidth = strokeSize,
        )
        drawLinearIndicator(
            startFraction = 0f,
            endFraction = progress,
            color = progressColor,
            strokeWidth = strokeSize,
        )
    }
}


@Composable
fun TimiCircularProgressBar(
    modifier: Modifier = Modifier,
    centerText: String,
    progress: Float,
    color: Color,
    backgroundColor: Color,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        TimiCaption2Regular(
            text = centerText,
            color = color,
        )
        CircularProgressBar(
            progress = progress,
            progressColor = color,
            backgroundColor = backgroundColor,
        )
    }
}

@Composable
private fun CircularProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    progressColor: Color,
    backgroundColor: Color,
) {
    val stroke = with(LocalDensity.current) {
        Stroke(
            width = CircularProgressBarStrokeWidth.toPx(),
            cap = StrokeCap.Round
        )
    }
    Canvas(
        modifier = modifier.size(CircularProgressBarSize)
    ) {
        val startAngle = 270f
        val sweep = progress * 360f
        drawCircularIndicatorBackground(
            color = backgroundColor,
            stroke = stroke,
        )
        drawCircularIndicator(
            startAngle = startAngle,
            sweep = sweep,
            color = progressColor,
            stroke = stroke,
        )
    }
}


private fun DrawScope.drawLinearIndicator(
    startFraction: Float,
    endFraction: Float,
    color: Color,
    strokeWidth: Float
) {
    val width = size.width
    val height = size.height
    val yOffset = height / 2

    val isLtr = layoutDirection == LayoutDirection.Ltr
    val barStart = (if (isLtr) startFraction else 1f - endFraction) * width
    val barEnd = (if (isLtr) endFraction else 1f - startFraction) * width

    drawLine(
        color = color,
        start = Offset(barStart, yOffset),
        end = Offset(barEnd, yOffset),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}

private fun DrawScope.drawLinearIndicatorBackground(
    color: Color,
    strokeWidth: Float
) = drawLinearIndicator(
    startFraction = 0f,
    endFraction = 1f,
    color = color,
    strokeWidth = strokeWidth
)

private fun DrawScope.drawCircularIndicator(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke
) {
    val diameterOffset = stroke.width / 2
    val arcDimen = size.width - 2 * diameterOffset
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweep,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arcDimen, arcDimen),
        style = stroke
    )
}

private fun DrawScope.drawCircularIndicatorBackground(
    color: Color,
    stroke: Stroke
) = drawCircularIndicator(
    startAngle = 270f,
    sweep = 360f,
    color = color,
    stroke = stroke,
)


@Preview
@Composable
fun ProgressBarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TimiLinearProgressBar(
            modifier = Modifier.padding(12.dp),
            progress = 0.5f,
            strokeWidth = 6.dp
        )
        TimiCircularProgressBar(
            progress = 1f,
            centerText = "D-day",
            color = Purple500,
            backgroundColor = Purple100,
        )
    }
}