package com.yapp.timitimi.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.theme.Black
import com.yapp.timitimi.theme.Body1_Medium
import com.yapp.timitimi.theme.Body2_Medium
import com.yapp.timitimi.theme.Body3_Regular
import com.yapp.timitimi.theme.Body4_Regular
import com.yapp.timitimi.theme.Button1_SemiBold
import com.yapp.timitimi.theme.Button2_Medium
import com.yapp.timitimi.theme.Caption1_Regular
import com.yapp.timitimi.theme.Caption1_SemiBold
import com.yapp.timitimi.theme.Caption2_Regular
import com.yapp.timitimi.theme.Caption2_SemiBold
import com.yapp.timitimi.theme.Caption3_Regular
import com.yapp.timitimi.theme.H1_Bold
import com.yapp.timitimi.theme.H1_SemiBold
import com.yapp.timitimi.theme.H2_SemiBold
import com.yapp.timitimi.theme.H3_SemiBold
import com.yapp.timitimi.theme.H4_SemiBold
import com.yapp.timitimi.theme.Purple500


@Composable
fun TimiH1Bold(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = H1_Bold.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiH1SemiBold(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = H1_SemiBold.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiH2SemiBold(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = H2_SemiBold.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiH3SemiBold(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = H3_SemiBold.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiH4SemiBold(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = H4_SemiBold.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiBody1Medium(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = Body1_Medium.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiBody2Medium(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = Body2_Medium.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiBody3Regular(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = Body3_Regular.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiBody4Regular(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = Body4_Regular.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiCaption1SemiBold(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = Caption1_SemiBold.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiCaption1Regular(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = Caption1_Regular.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiCaption2SemiBold(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = Caption2_SemiBold.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiCaption2Regular(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = Caption2_Regular.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiCaption3Regular(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = Caption3_Regular.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiButton1SemiBold(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = Button1_SemiBold.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiButton2Medium(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    textAlign: TextAlign? = null,
    color: Color = Black,
    singleLine: Boolean = false,
) = TimiBasicText(
    modifier = modifier.timiClickable(
        onClick = onClick,
        rippleEnabled = rippleEnabled,
    ),
    text = text,
    style = Button2_Medium.copy(
        color = color,
        textAlign = textAlign,
    ),
    singleLine = singleLine,
)

@Composable
fun TimiAnnotatedText(
    modifier: Modifier = Modifier,
    textPairs: List<Pair<String, Color>>,
    textStyle: TextStyle = TextStyle.Default,
    onClick: (() -> Unit)? = null,
    rippleEnabled: Boolean = false,
    singleLine: Boolean = false,
    textAlign: TextAlign? = null,
    ) {
    val annotatedText = buildAnnotatedString {
        textPairs.forEach {
            withStyle(
                SpanStyle(
                    color = it.second,
                )
            ) {
                append(it.first)
            }
        }
    }
    BasicText(
        modifier = modifier.timiClickable(
            onClick = onClick,
            rippleEnabled = rippleEnabled,
        ),
        text = annotatedText,
        style = textStyle.copy(
            textAlign = textAlign,
        ),
        maxLines = when (singleLine) {
            true -> 1
            else -> Int.MAX_VALUE
        },
    )
}

@Composable
private fun TimiBasicText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    singleLine: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) = BasicText(
    modifier = modifier,
    text = text,
    style = style,
    maxLines = when (singleLine) {
        true -> 1
        else -> Int.MAX_VALUE
    },
    overflow = overflow,
)

@Composable
private fun TimiBasicText(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    style: TextStyle,
    singleLine: Boolean = true,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) = BasicText(
    modifier = modifier,
    text = text,
    style = style,
    maxLines = when (singleLine) {
        true -> 1
        else -> Int.MAX_VALUE
    },
    overflow = overflow,
)


@Preview
@Composable
fun TimiTypographyPreview() {
    val text = "티미 타이포 입니다"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TimiH1Bold(text = text)
        TimiH1SemiBold(text = text)
        TimiH2SemiBold(text = text)
        TimiH3SemiBold(text = text)
        TimiH4SemiBold(text = text)
        TimiBody1Medium(text = text)
        TimiBody2Medium(text = text)
        TimiBody3Regular(text = text)
        TimiBody4Regular(text = text)
        TimiCaption1SemiBold(text = text)
        TimiCaption1Regular(text = text)
        TimiCaption2SemiBold(text = text)
        TimiCaption2Regular(text = text)
        TimiCaption3Regular(text = text)
        TimiButton1SemiBold(text = text)
        TimiButton2Medium(text = text)
        TimiAnnotatedText(
            textPairs = listOf(
                Pair("가연", Purple500),
                Pair("님을 삭제 하시겠어요?", Black)
            ),
            textStyle = H3_SemiBold
        )
    }
}