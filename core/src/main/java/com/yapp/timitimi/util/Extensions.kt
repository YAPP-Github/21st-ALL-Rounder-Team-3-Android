package com.yapp.timitimi.util

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt

fun Float.dp(context: Context): Int {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, metrics).roundToInt()
}

fun Int.dp(context: Context) = toFloat().dp(context)
