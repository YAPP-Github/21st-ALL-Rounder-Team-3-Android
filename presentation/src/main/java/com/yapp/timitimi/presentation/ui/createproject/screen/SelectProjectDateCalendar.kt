package com.yapp.timitimi.presentation.ui.createproject.screen

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.yapp.timitimi.presentation.R

@Composable
fun SelectProjectDateCalendar(
    onStartDueDateFilled: (String) -> Unit,
    onEndDueDateFilled: (String) -> Unit,
    onDismissed: () -> Unit,
    calendarType: CalenderDueDateType
) {
    Dialog(
        onDismissRequest = onDismissed
    ) {
        AndroidView(
            {
                CalendarView(android.view.ContextThemeWrapper(it, R.style.CustomCalendar)).apply {
                    dateTextAppearance = R.style.CustomDay
                    weekDayTextAppearance = R.style.CustomWeek
                }
            },
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
              .background(Color.White),
            update = { views ->
                views.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                    when (calendarType) {
                        CalenderDueDateType.START -> onStartDueDateFilled(
                            CalendarDate(
                                day = dayOfMonth,
                                month = month,
                                year = year
                            ).convertDate()
                        )

                        CalenderDueDateType.END -> onEndDueDateFilled(
                            CalendarDate(
                                day = dayOfMonth,
                                month = month,
                                year = year
                            ).convertDate()
                        )

                        else -> Unit
                    }
                }
            }
        )
    }
}

data class CalendarDate(
    val day: Int,
    val month: Int,
    val year: Int
) {
    private fun Int.convertMM(): String {
        return if (this < 10) {
            "0$this"
        } else {
            this.toString()
        }
    }

    fun convertDate(): String {
        return "${year}-${month.convertMM()}-${day.convertMM()}"
    }
}

enum class CalenderDueDateType {
    NONE, START, END
}