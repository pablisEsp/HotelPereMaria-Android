package com.example.hotelperemaria.home

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
    PantallaInicioConTutorial(habitaciones, navController)
}

