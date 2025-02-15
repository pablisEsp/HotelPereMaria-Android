package com.example.hotelperemaria.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.utils.Config.SERVER_IP

@Composable
fun HabitacionPage(habitacion: Habitacion, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {

        val imagenUrl = (SERVER_IP + habitacion.imagenes?.firstOrNull()?.takeIf { it.isNotBlank() })


        // 📌 Cargar imagen de fondo usando Coil ca
        AsyncImage(
            model = imagenUrl,
            contentDescription = "Imagen de la habitación",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 📌 Nombre de la habitación y botón
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = habitacion.categoria,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextButton(
                onClick = {
                    // 📌 Navegar a la pantalla de detalles con el código de la habitación
                    navController.navigate("room_screen/${habitacion.codigo}")
                },
                modifier = Modifier
                    .background(Color.White, shape = CircleShape)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Ver Detalles",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
        }
    }
}
