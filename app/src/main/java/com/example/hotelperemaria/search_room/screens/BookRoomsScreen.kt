package com.example.hotelperemaria.search_room.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter

@Composable
fun BookRoomsScreen(navController: NavController) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 30.dp),
    ) {
        AsyncImage(
            model = "https://www.kakslauttanen.fi/wp-content/uploads/2013/09/MAJOITUS_Lasi-iglut_1.jpg",
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
    }

}


