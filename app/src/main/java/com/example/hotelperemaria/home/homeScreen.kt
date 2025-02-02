package com.example.hotelperemaria.home

import PantallaInicio
import PantallaInicioConTutorial
import android.content.Context
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hotelperemaria.R
import com.example.hotelperemaria.rooms.model.Cama
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.utils.BottomAppBarCustom


// Datos de ejemplo
val habitaciones = listOf(
    Habitacion(
        codigo = "ODIN-001",
        nombre = "Suite Odin",
        categoria = "Suite",
        camas = listOf(Cama(1, "King-size")),
        tamanyo = 45.0,
        servicios = listOf("WiFi", "Jacuzzi", "Vistas al mar", "Desayuno incluido"),
        numPersonas = 2,
        precio = 250.0,
        descripcion = "Una suite lujosa con vistas impresionantes al mar.",
        imagenes = listOf(
            R.drawable.suiteodinroom,
            R.drawable.suiteodinroom,
            R.drawable.suiteodinroom
        ),
        habilitada = true
    ),
    Habitacion(
        codigo = "JR-002",
        nombre = "Junior Suite",
        categoria = "Suite",
        camas = listOf(Cama(1, "Doble")),
        tamanyo = 35.0,
        servicios = listOf("WiFi", "Vistas a la ciudad", "Mini bar"),
        numPersonas = 2,
        precio = 180.0,
        descripcion = "Junior suite con vistas a la ciudad y ambiente acogedor.",
        imagenes = listOf(
            R.drawable.juniorsuite,
            R.drawable.juniorsuite,
            R.drawable.juniorsuite
        ),
        habilitada = true
    ),
    Habitacion(
        codigo = "PR-003",
        nombre = "Habitación Premium",
        categoria = "Premium",
        camas = listOf(Cama(1, "King-size")),
        tamanyo = 40.0,
        servicios = listOf("WiFi", "Jacuzzi privado", "Smart TV", "Balcón privado"),
        numPersonas = 2,
        precio = 220.0,
        descripcion = "Habitación premium con jacuzzi y smart TV para una estancia de lujo.",
        imagenes = listOf(
            R.drawable.premiumroom,
            R.drawable.premiumroom,
            R.drawable.premiumroom
        ),
        habilitada = true
    ),
    Habitacion(
        codigo = "ST-004",
        nombre = "Habitación Estándar - Matrimonio",
        categoria = "Estándar",
        camas = listOf(Cama(1, "Doble")),
        tamanyo = 25.0,
        servicios = listOf("WiFi", "Desayuno opcional", "TV por cable"),
        numPersonas = 2,
        precio = 100.0,
        descripcion = "Habitación estándar con cama doble, ideal para parejas.",
        imagenes = listOf(
            R.drawable.standardroom,
            R.drawable.standardroom,
            R.drawable.standardroom
        ),
        habilitada = true
    ),
    Habitacion(
        codigo = "ST-005",
        nombre = "Habitación Estándar - Twin",
        categoria = "Estándar",
        camas = listOf(Cama(2, "Individual")),
        tamanyo = 25.0,
        servicios = listOf("WiFi", "TV por cable", "Aire acondicionado"),
        numPersonas = 2,
        precio = 100.0,
        descripcion = "Habitación estándar con dos camas individuales, ideal para amigos o compañeros de trabajo.",
        imagenes = listOf(
            R.drawable.standardroom_twin,
            R.drawable.standardroom_twin,
            R.drawable.standardroom_twin
        ),
        habilitada = true
    )
)


@Composable
fun HomeScreen(navController: NavController) {
    PantallaInicioConTutorial(habitaciones, navController)
}

