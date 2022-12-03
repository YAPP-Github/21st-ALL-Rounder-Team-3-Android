package com.yapp.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.yapp.presentation.R

val NotoSansKr = FontFamily(
    Font(R.font.notosans_medium, FontWeight.Medium),
    Font(R.font.notosans_regular, FontWeight.Normal),
    Font(R.font.notosans_light, FontWeight.Light)
)

val H1 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Light,
    fontSize = 96.sp,
    letterSpacing = (-1.5).sp,
)

val H2 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.W600,
    fontSize = 20.sp,
    lineHeight = 28.sp,
    letterSpacing = (0.5).sp,
)

val H3 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Normal,
    fontSize = 48.sp,
    letterSpacing = 0.sp,
)

val H4 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Normal,
    fontSize = 34.sp,
    letterSpacing = (0.25).sp
)

val H5 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Light,
    fontSize = 24.sp,
    letterSpacing = 0.sp
)

val H6 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Medium,
    fontSize = 20.sp,
    letterSpacing = (0.15).sp
)

val Subtitle1 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    letterSpacing = (0.15).sp
)

val Subtitle2 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    letterSpacing = (0.1).sp
)

val Body1 = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    letterSpacing = (0.5).sp
)

val Body2 = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    letterSpacing = (0.25).sp
)

val Button = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    letterSpacing = (1.25).sp
)

val Caption = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    letterSpacing = (0.4).sp
)

val OverLine = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Normal,
    fontSize = 10.sp,
    letterSpacing = (1.5).sp
)

val Typography = Typography(
    h1 = H1,
    h2 = H2,
    h3 = H3,
    h4 = H4,
    h5 = H5,
    h6 = H6,
    subtitle1 = Subtitle1,
    subtitle2 = Subtitle2,
    body1 = Body1,
    body2 = Body2,
    button = Button,
    caption = Caption,
    overline = OverLine,
)