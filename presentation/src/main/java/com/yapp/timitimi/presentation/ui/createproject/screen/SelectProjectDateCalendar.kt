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


@Composable
fun SelectProjectDateCalendar(
    onStartDueDateFilled: (CalendarDate) -> Unit,
    onEndDueDateFilled: (CalendarDate) -> Unit,
    onDismissed: () -> Unit,
    calendarType: CalenderDueDateType
) {
    Dialog(
        onDismissRequest = onDismissed
    ) {
        AndroidView(
            { CalendarView(it) },
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
                            )
                        )

                        CalenderDueDateType.END -> onEndDueDateFilled(
                            CalendarDate(
                                day = dayOfMonth,
                                month = month,
                                year = year
                            )
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
)

enum class CalenderDueDateType {
    NONE, START, END
}