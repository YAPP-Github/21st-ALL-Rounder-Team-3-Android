package com.yapp.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.yapp.presentation.R

val NotoSansKr = FontFamily(
    Font(R.font.notosans_bold, FontWeight.Bold),
    Font(R.font.notosans_semibold, FontWeight.SemiBold),
    Font(R.font.notosans_medium, FontWeight.Medium),
    Font(R.font.notosans_regular, FontWeight.Normal),
    Font(R.font.notosans_light, FontWeight.Light),
)

val H1 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.W600,
    fontSize = 24.sp,
    lineHeight = 33.6.sp,
    letterSpacing = 0.16.sp
)

val H2 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.W600,
    fontSize = 20.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.16.sp
)

val H3 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.W600,
    fontSize = 16.sp,
    lineHeight = 22.4.sp,
    letterSpacing = 0.16.sp
)

val H4 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.W600,
    fontSize = 14.sp,
    lineHeight = 19.6.sp,
    letterSpacing = 0.16.sp
)

val Body1 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp,
    lineHeight = (19.6).sp
)

val Body2 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.W400,
    fontSize = 14.sp,
    lineHeight = (19.6).sp
)

val Body3 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.W400,
    fontSize = 12.sp,
    lineHeight = (16.8).sp
)

val Caption1 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.W400,
    fontSize = 12.sp,
    lineHeight = (16.8).sp
)

val Caption2 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.W400,
    fontSize = 10.sp,
    lineHeight = (14).sp
)

val Button1 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.W600,
    fontSize = 16.sp,
    lineHeight= (22.4).sp
)

val Button2 = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = (19.6).sp
)

val Typography = Typography(
    h1 = H1,
    h2 = H2,
    h3 = H3,
    h4 = H4,
    body1 = Body1,
    body2 = Body2,
)