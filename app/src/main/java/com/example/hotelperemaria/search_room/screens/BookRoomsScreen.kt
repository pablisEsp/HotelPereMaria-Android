package com.example.hotelperemaria.search_room.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
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

        }


    }

}