package com.example.hotelperemaria.rooms.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.rooms.model.Servicio
import com.example.hotelperemaria.rooms.model.getPainterFromName
import com.example.hotelperemaria.rooms.model.mapServicios
import com.example.hotelperemaria.rooms.viewmodel.RoomViewModel

@Composable
fun RoomDetailScreen(idHabitacion: String, navController: NavController) {
    val roomViewModel: RoomViewModel = viewModel()

    val habitacion by roomViewModel.habitacion.collectAsState()
    val isLoading by roomViewModel.isLoading.collectAsState()

    // Llamamos a la API cuando la pantalla se carga
    LaunchedEffect(idHabitacion) {
        roomViewModel.fetchHabitacion(idHabitacion)
    }

    if (isLoading) {
        // 📌 Mostrar indicador de carga
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (habitacion == null) {
        // 📌 Mostrar mensaje si no se encuentra la habitación
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Habitación no encontrada", color = Color.Red, fontSize = 18.sp)
        }
        return
    }

    val servicios = mapServicios(habitacion!!.serviciosString)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ImageCarousel(images = habitacion!!.imagenes)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = habitacion!!.nombre,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = habitacion!!.descripcion,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        RoomServices(services = servicios)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = { /* Navegar a la pantalla de reserva */ },
                modifier = Modifier.fillMaxWidth(),
                shape = CircleShape
            ) {
                Text(text = "Reservar ahora", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun ImageCarousel(images: List<String>) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) { page ->
        AsyncImage(
            model = images[page],  // Carga la imagen desde la URL
            contentDescription = "Imagen de la habitación",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * Mostrar iconos en `RoomServices`
 */
@Composable
fun RoomServices(services: List<Servicio>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        services.forEach { servicio ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = getPainterFromName(servicio.icono),
                    contentDescription = servicio.nombre,
                    modifier = Modifier.size(32.dp)
                )
                Text(text = servicio.nombre, fontSize = 12.sp, color = Color.Black)
            }
        }
    }
}