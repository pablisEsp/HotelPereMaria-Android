package com.example.hotelperemaria.search_room.screens

import ButtonCustom
import GuestSelector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hotelperemaria.R
import com.example.hotelperemaria.search_room.screens.widgets.DatePickerDialogCustom
import com.example.hotelperemaria.ui.theme.darkGray
import com.example.hotelperemaria.utils.BottomAppBarCustom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookRoomsScreen(navController: NavController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Fondo de pantalla
            Image(
                painter = painterResource(id = R.drawable.fondoreservas),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Contenido de la pantalla
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(R.drawable.asgard_easter_egg_dorado),
                    contentDescription = null
                )
                DatePickerDialogCustom(modifier = Modifier)
                Spacer(modifier = Modifier.height(15.dp))
                DatePickerDialogCustom(modifier = Modifier)
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Huespedes", color = Color(0xFF36454F))
                Spacer(modifier = Modifier.height(5.dp))
                // Selector de huéspedes mejorado
                GuestSelector(
                    modifier = Modifier.fillMaxWidth(),
                    onGuestCountChanged = { count ->
                        // Aquí puedes manejar el cambio en el número de huéspedes
                        println("Número de huéspedes seleccionado: $count")
                    }
                )

                Spacer(modifier = Modifier.height(15.dp))
                ButtonCustom(onClick = {})
            }
        }

}

