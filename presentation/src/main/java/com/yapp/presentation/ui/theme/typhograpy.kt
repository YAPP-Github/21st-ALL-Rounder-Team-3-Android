package com.yapp.presentation.ui.theme

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

val H1_Bold = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    lineHeight = (33.6).sp,
)

val H1_SemiBold = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = (33.6).sp,
)

val H2_SemiBold = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    lineHeight = 28.sp,
)

val H3_SemiBold = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = (22.4).sp,
)

val H4_SemiBold = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    lineHeight = (19.6).sp,
)

val Body1_Medium = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = (22.4).sp
)

val Body2_Medium = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = (19.6).sp
)

val Body3_Regular = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = (19.6).sp
)

val Body4_Regular = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = (16.8).sp
)

val Caption1_SemiBold = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.SemiBold,
    fontSize = 12.sp,
    lineHeight = (16.8).sp
)

val Caption1_Regular = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = (16.8).sp
)

val Caption2_SemiBold = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.SemiBold,
    fontSize = 10.sp,
    lineHeight = 14.sp
)

val Caption2_Regular = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Normal,
    fontSize = 10.sp,
    lineHeight = 14.sp
)

val Caption3_Regular = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Normal,
    fontSize = 8.sp,
    lineHeight = (11.2).sp
)

val Button1_SemiBold = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight= (22.4).sp
)

val Button2_Medium = TextStyle(
    fontFamily = NotoSansKr,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = (19.6).sp
)