package com.yapp.presentation.ui.createproject.onestep

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.yapp.presentation.ui.createproject.base.CreateProjectIntent
import com.yapp.presentation.ui.createproject.CreateProjectViewModel


@Composable
fun SelectProjectDateCalendar(
    viewModel: CreateProjectViewModel,
    calendarType: CreateProjectIntent.DueDateType
) {
    Dialog(
        onDismissRequest = {
            viewModel.dispatch(CreateProjectIntent.CloseCalendar)
        }
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
                        CreateProjectIntent.DueDateType.START -> viewModel.dispatch(
                            CreateProjectIntent.SelectStartProjectDate(
                                day = dayOfMonth,
                                month = month,
                                year = year
                            )
                        )

                        CreateProjectIntent.DueDateType.END -> viewModel.dispatch(
                            CreateProjectIntent.SelectEndProjectDate(
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