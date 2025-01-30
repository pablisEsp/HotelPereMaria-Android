package com.example.hotelperemaria.search_room.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.hotelperemaria.R
import com.example.hotelperemaria.home.Habitacion

@Composable
fun ChoseRoomScreen(habitaciones: List<Habitacion>) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Fondo de pantalla
        Image(
            painter = painterResource(id = R.drawable.fondoreservas),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        LazyColumn {

        }




    }

}