package com.example.hotelperemaria.search_room.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hotelperemaria.R
import com.example.hotelperemaria.utils.BottomAppBarCustom

@Composable
fun BookRoomsScreen(navController: NavController) {
    // Estados para los campos
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var guests by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = { BottomAppBarCustom() }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Parte superior con la imagen de fondo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Ocupa una parte del espacio
            ) {
                Image(
                    painter = painterResource(R.drawable.arbol_bien),
                    contentDescription = "Fondo de imagen",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Texto sobre la imagen
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Encuentra la habitación que necesitas",
                        style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Parte inferior con el fondo blanco y los campos
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f) // Ocupa el resto del espacio
                    .background(Color.White)
                    .padding( vertical = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Contenedor de campos
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Campo de fecha de inicio
                           DatePicker()

                            Spacer(modifier = Modifier.height(16.dp))



                            // Campo de número de huéspedes
                            OutlinedTextField(
                                value = guests,
                                onValueChange = { guests = it },
                                label = { Text("Número de huéspedes") },
                                placeholder = { Text("Ejemplo: 2") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Botón Buscar
                    Button(
                        onClick = {
                            // Lógica de búsqueda
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726))
                    ) {
                        Text(
                            text = "Buscar",
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
