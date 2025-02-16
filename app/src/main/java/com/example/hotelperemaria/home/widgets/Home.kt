package com.example.hotelperemaria.home.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelperemaria.R

@Composable
fun Home() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fondoreservas), // Reemplaza con tu imagen de fondo
            contentDescription = "Fondo del Hotel Asgard",
            contentScale = ContentScale.FillBounds, // Para que la imagen cubra toda la pantalla
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Imagen del icono del hotel
            Image(
                painter = painterResource(id = R.drawable.asgard_easter_egg_dorado),
                contentDescription = "Icono del Hotel Asgard",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 100.dp)
            )

            Spacer(Modifier.height(16.dp))

            // Título y descripción
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Descubre la belleza nórdica",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Explora nuestras habitaciones únicas y disfruta de una experiencia inolvidable en el corazón del norte.",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(Modifier.height(140.dp))
            // Animaciones de deslizamiento
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                SlidingTextToLeft(
                    text = "Desliza para reservar",
                    icon = Icons.AutoMirrored.Filled.ArrowForward
                )
                SlidingTextToRight(
                    text = "Desliza para explorar",
                    icon = Icons.AutoMirrored.Filled.ArrowForward
                )
            }
        }
    }
}
