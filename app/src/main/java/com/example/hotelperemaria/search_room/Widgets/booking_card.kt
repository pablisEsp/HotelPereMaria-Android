package com.example.hotelperemaria.search_room.Widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hotelperemaria.home.Habitacion
import com.example.hotelperemaria.ui.theme.darkGray
import com.example.hotelperemaria.ui.theme.lightGray
import com.example.hotelperemaria.ui.theme.silver
import com.example.hotelperemaria.ui.theme.uranianBlue
import com.example.hotelperemaria.ui.theme.white

@Composable
fun HotelReservationCard(habitacion: Habitacion?) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = darkGray.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = habitacion!!.imagen), // Reemplaza con tu imagen
                contentDescription = "Suit Room",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = habitacion.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    color = white
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = habitacion!!.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = silver
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "178 € por noche",
                    style = MaterialTheme.typography.headlineMedium,
                    color = uranianBlue
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Acción de reserva */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lightGray.copy(alpha = 0.8f),
                        contentColor = white
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Reservar ahora")
                }
            }
        }
    }
}


@Preview
@Composable
fun hotelReservaPreview(){
    HotelReservationCard(habitacion = null)
}
