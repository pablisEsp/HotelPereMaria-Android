package com.example.hotelperemaria.search_room.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hotelperemaria.R
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel
import com.example.hotelperemaria.utils.colorsOutfield

@Composable
fun BookingDetailsView(
    viewModel : BookRoomViewModel
){
    val bookRoomState by viewModel.bookRoomState.collectAsState()

    Box (
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.fondoreservas),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        OutlinedTextField(
            colors = colorsOutfield ,
            shape = CircleShape,
            value = bookRoomState.startDate,
            enabled = false,
            onValueChange = { it ->
             //   value = value + it
            },
            label = { Text(text = "Fecha de Entrada") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {}) {
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
    }
}