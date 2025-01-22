package com.example.hotelperemaria.search_room.screens.date_picker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelperemaria.ui.theme.HotelPereMariaTheme
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerCustom() {
    val dateTime = LocalDateTime.now()
    val dateState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = dateTime.toMillis(),
        initialDisplayedMonthMillis = null,
        initialSelectedEndDateMillis = dateTime.plusDays(3).toMillis(),
        initialDisplayMode = DisplayMode.Input,
        yearRange = (2004..2025)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select Date Range",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        DateRangePicker(
            state = dateState,
            modifier = Modifier.fillMaxWidth(),
            colors = DatePickerDefaults.colors(
                containerColor = Color(0xFFF6F6F6),
                titleContentColor = MaterialTheme.colorScheme.primary,
                headlineContentColor = MaterialTheme.colorScheme.primary,
                weekdayContentColor = MaterialTheme.colorScheme.onSurface,
                dayContentColor = MaterialTheme.colorScheme.primary,
                selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
                selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                todayDateBorderColor = MaterialTheme.colorScheme.secondary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (dateState.selectedStartDateMillis != null && dateState.selectedEndDateMillis != null) {
            val startDate = dateState.selectedStartDateMillis!!.toLocalDate()
            val endDate = dateState.selectedEndDateMillis!!.toLocalDate()

            Text(
                text = "Selected Range: $startDate - $endDate",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

fun LocalDateTime.toMillis() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

fun Long.toLocalDate(): String {
    val date = java.time.Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()
    return date.toString()
}

@Composable
@Preview
fun datepickerPreview() {
    HotelPereMariaTheme {
        DatePickerCustom()
    }
}
