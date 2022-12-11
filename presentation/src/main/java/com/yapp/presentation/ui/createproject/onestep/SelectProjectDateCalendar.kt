package com.yapp.presentation.ui.createproject.onestep

import android.app.DatePickerDialog
import android.content.Context
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.yapp.presentation.R
import com.yapp.presentation.ui.createproject.base.CreateProjectIntent
import com.yapp.presentation.ui.createproject.CreateProjectViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


@Composable
fun DatePickerDialog(
    viewModel: CreateProjectViewModel,
    calendarType: CreateProjectIntent.DueDateType,
    dateOfBirth: String
) {
    val context = LocalContext.current
    val calendar = getCalendar(dateOfBirth)
    DatePickerDialog(
        context,
        R.style.Theme_DialogTheme,
        { _, year, month, day ->
            when (calendarType) {
                CreateProjectIntent.DueDateType.START -> viewModel.dispatch(
                    CreateProjectIntent.SelectStartProjectDate(
                        day = day,
                        month = month,
                        year = year
                    )
                )

                CreateProjectIntent.DueDateType.END -> {
                    viewModel.selectEndDate(
                        day = day,
                        month = month,
                        year = year
                    )
                }
                else -> Unit
            }
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}

private fun getCalendar(dateOfBirth: String): Calendar {
    return if (dateOfBirth.isEmpty()) {
        Calendar.getInstance()
    } else {
        getLastPickedDateCalendar(dateOfBirth)
    }
}

private fun getLastPickedDateCalendar(dateOfBirth: String): Calendar {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.time = dateFormat.parse(dateOfBirth)
    return calendar
}

