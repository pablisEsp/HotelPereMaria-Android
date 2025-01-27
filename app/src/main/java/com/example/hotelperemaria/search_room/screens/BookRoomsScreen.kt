package com.example.hotelperemaria.search_room.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.hotelperemaria.search_room.screens.Widgets.DatePickerCustom
import com.example.hotelperemaria.utils.BottomAppBarCustom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookRoomsScreen(navController: NavController) {
    // Estados para los campos
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var guests by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = { BottomAppBarCustom() }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .fillMaxSize()) {
                DatePickerCustom()
        }


    }


    DatePicker()
}