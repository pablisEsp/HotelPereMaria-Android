package com.example.hotelperemaria.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose
    .material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hotelperemaria.R
import com.example.hotelperemaria.utils.BottomAppBarCustom

// Datos de ejemplo
data class Habitacion(val id: Int, val nombre: String, val descripcion: String, val imagen: Int)

val habitaciones = listOf(
    Habitacion(1, "Suite Odin", "Cama king-size, vista al mar.", R.drawable.suiteodinroom),
    Habitacion(2, "Junior Suite", "Cama doble, vista a la ciudad.", R.drawable.juniorsuite),
    Habitacion(3, "Habitación Premium", "Cama king-size, jacuzzi privado.", R.drawable.premiumroom),
    Habitacion(4, "Habitación Estándar - Matrimonio", "Cama doble, ideal para presupuestos ajustados.", R.drawable.standardroom),
    Habitacion(5, "Habitación Estándar - Twin", "Cama doble, ideal para presupuestos ajustados.", R.drawable.standardroom_twin)

)

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomAppBar {
                BottomAppBarCustom()
            }
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Imagen en la parte superior
            Image(
                painter = painterResource(id = R.drawable.suiteodinlivingroom),
                contentDescription = "Imagen del hotel",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Ajusta la altura según lo necesario
                    .padding(top = 0.dp),
                contentScale = ContentScale.Crop // Escala la imagen para que ocupe todo el ancho
            )

            // Lista de habitaciones debajo de la imagen
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(habitaciones) { habitacion ->
                    HabitacionCard(habitacion = habitacion) {
                        navController.navigate("detalle_habitacion/${habitacion.id}")
                    }                }
            }
        }
    }
}

@Composable
fun HabitacionCard(habitacion: Habitacion, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable { onClick() }, //makes the card clickable
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = habitacion.nombre,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Image(
                painter = painterResource(id = habitacion.imagen),
                contentDescription = habitacion.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = habitacion.descripcion,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.surface
            )
        }
    }
}
