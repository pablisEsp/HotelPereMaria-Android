package com.example.hotelperemaria.search_room.views.widgets

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hotelperemaria.ui.theme.darkGray
import com.example.hotelperemaria.ui.theme.lightGray
import com.example.hotelperemaria.ui.theme.white
import com.example.hotelperemaria.ui.theme.whitesmoke
import com.example.hotelperemaria.utils.colorsOutfield
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogCustom(modifier: Modifier,
                           initialDate: String,
                           onDateSelected: (String) -> Unit,
                         ) {

    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var selectedDate by remember {
        mutableStateOf(initialDate)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        OutlinedTextField(
            colors = colorsOutfield ,
            shape = CircleShape,
            value = selectedDate,
            enabled = false,
            onValueChange = {},
            label = { Text(text = "Selecione Una Fecha") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )
        if (showDatePicker) {
            DatePickerModal(
                datePickerState = datePickerState,
                onDateSelected = { millis ->
                    millis?.let {
                        val formatedDate = convertMillisToDate(it)
                        selectedDate = formatedDate
                        onDateSelected(formatedDate)
                    }
                }) {
                showDatePicker = false
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    datePickerState: DatePickerState,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,

    ) {

    DatePickerDialog(

        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK", color = white)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = white)
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = lightGray, // Fondo azul oscuro
            titleContentColor = white, // Título blanco
            headlineContentColor = white, // Texto principal
            weekdayContentColor = darkGray, // Días de la semana
            dayContentColor = whitesmoke, // Días normales
            selectedDayContentColor = white, // Día seleccionado
            selectedDayContainerColor = darkGray.copy(alpha = 0.8f), // Fondo del día seleccionado
            todayContentColor = Color.Black, // Hoy resaltado
            disabledDayContentColor = whitesmoke.copy(alpha = 0.3f), // Días deshabilitados
        )
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = darkGray.copy(alpha = 0.3f), // Fondo del día seleccionado
                selectedDayContentColor = whitesmoke, // Texto del día seleccionado
                todayDateBorderColor = whitesmoke,
                todayContentColor = whitesmoke// Borde rojo para el día de hoy
            ),

            )
    }
}


