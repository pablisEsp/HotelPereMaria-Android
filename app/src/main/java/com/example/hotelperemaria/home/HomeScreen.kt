package com.example.hotelperemaria.home

import MainScreenPager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotelperemaria.rooms.viewmodel.RoomViewModel
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModelBookRoom: BookRoomViewModel,
    viewModel: RoomViewModel = hiltViewModel()
) {
    val uniqueRooms by viewModel.uniqueRooms.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Ejecutar la llamada a la API solo cuando la pantalla se carga hh
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
        MainScreenPager(habitaciones = uniqueRooms, navController, viewModelBookRoom)
    }
}
