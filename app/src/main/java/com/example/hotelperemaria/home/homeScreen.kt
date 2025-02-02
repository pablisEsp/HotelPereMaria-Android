package com.example.hotelperemaria.home

import PantallaInicio
import PantallaInicioConTutorial
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose
    .material.icons.filled.*
import androidx.compose.material.icons.filled.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hotelperemaria.R
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.rooms.viewmodel.RoomViewModel
import com.example.hotelperemaria.utils.BottomAppBarCustom




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


