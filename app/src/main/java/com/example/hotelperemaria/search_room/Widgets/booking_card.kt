package com.example.hotelperemaria.search_room.Widgets

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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.ui.theme.darkGray
import com.example.hotelperemaria.ui.theme.lightGray
import com.example.hotelperemaria.ui.theme.silver
import com.example.hotelperemaria.ui.theme.uranianBlue
import com.example.hotelperemaria.ui.theme.white
import com.example.hotelperemaria.utils.Config.SERVER_IP

@Composable
fun HotelReservationCard(
    habitacion: Habitacion?,
    selectRoom: (Habitacion) -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = darkGray.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val imagenUrl = (SERVER_IP + habitacion!!.imagenes?.firstOrNull()?.takeIf { it.isNotBlank() })

        Column {
            AsyncImage(
                model =imagenUrl, // Reemplaza con tu imagen
                contentDescription = habitacion.descripcion,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = habitacion.categoria,
                    style = MaterialTheme.typography.titleMedium,
                    color = white
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = habitacion.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = silver
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "${habitacion.precio}€ / noche",
                    style = MaterialTheme.typography.headlineSmall,
                    color = white
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { selectRoom(habitacion) } ,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lightGray.copy(alpha = 0.8f),
                        contentColor = white
                    ),
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Reservar ahora")
                }
            }
        }
    }
}



