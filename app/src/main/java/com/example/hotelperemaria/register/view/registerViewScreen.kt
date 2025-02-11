package com.example.hotelperemaria.register.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun pantalla(){
    Box(modifier = Modifier.fillMaxSize().background(color = Color.White).
        padding(horizontal = 50.dp, vertical = 100.dp)
    ){
        Column {
            nameField()
            Spacer(Modifier.padding(10.dp))
            surnameField()
            Spacer(Modifier.padding(10.dp))
            emailField()
        }
    }
}

@Composable
fun nameField(){
    var nombre by remember { mutableStateOf("")}

    TextField(
        value = nombre,
        onValueChange = {nombre = it},
        label = { Text("Escriba su nombre")}
    )
}

@Composable
fun surnameField(){
    var nombre by remember { mutableStateOf("")}

    TextField(
        value = nombre,
        onValueChange = {nombre = it},
        label = { Text("Escriba su apellido")}
    )

}

@Composable
fun emailField(){
    var nombre by remember { mutableStateOf("")}

    TextField(
        value = nombre,
        onValueChange = {nombre = it},
        label = { Text("Escriba su correo")}
    )

}

@Composable
@Preview
fun showView(){
    pantalla()
}