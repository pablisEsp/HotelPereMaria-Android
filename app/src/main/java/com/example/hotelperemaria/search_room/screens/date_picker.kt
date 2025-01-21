package com.example.hotelperemaria.search_room.screens

import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(){
    val dateTime = LocalDateTime.now()
    val dateState = rememberDateRangePickerState(
            initialSelectedStartDateMillis = dateTime.toMillis(),
            initialDisplayedMonthMillis = null,
            initialSelectedEndDateMillis = dateTime.plusDays(3).toMillis(),
            initialDisplayMode = DisplayMode.Input,
            yearRange = (2004 .. 2025)
    )
    DateRangePicker(state = dateState)
}

fun LocalDateTime.toMillis() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

@Composable
@Preview
fun datepickerPreview(){
    DatePicker()
}