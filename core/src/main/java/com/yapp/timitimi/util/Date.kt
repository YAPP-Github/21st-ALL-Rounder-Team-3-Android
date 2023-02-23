package com.yapp.timitimi.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun String.toDotDate() = drop(5).replace("-", ".")

@RequiresApi(Build.VERSION_CODES.O)
fun String.getDdayFromToday(): Int {
    val currentDate = LocalDate.now()
    val targetDate = LocalDate.parse(this, DateTimeFormatter.ISO_DATE)
    return ChronoUnit.DAYS.between(currentDate, targetDate).toInt()
}

fun Int.toStringDday(): String {
    return if (this > 0) {
        "D-$this"
    } else {
        "D-Day"
    }
}