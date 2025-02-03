package com.example.hotelperemaria.home

import PantallaInicioConTutorial
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hotelperemaria.R
import com.example.hotelperemaria.utils.BottomAppBarCustom

// Datos de ejemplo
data class Habitacion(val id: Int, val nombre: String, val descripcion: String, val imagen: Int)


@Composable
fun HomeScreen(navController: NavController, viewModel: RoomViewModel = viewModel()) {
    val uniqueRooms by viewModel.uniqueRooms.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Ejecutar la llamada a la API solo cuando la pantalla se carga
    LaunchedEffect(Unit) {
        if (uniqueRooms.isEmpty()) { // Evita llamadas innecesarias
            viewModel.fetchUniqueRooms()
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        PantallaInicioConTutorial(habitaciones = uniqueRooms, navController)
    }
}


// cambios hechos :)
