package com.example.hotelperemaria.home.widgets

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SlidingTextToLeft(
    text: String,
    icon: ImageVector
) {
    val infiniteTransition = rememberInfiniteTransition()

    // Animar desplazamiento hacia la izquierda
    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -24f, // Moverse 30dp hacia la izquierda
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000), // Ciclo de 800ms
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Row(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .offset(x = offsetX.dp) // Aplicar desplazamiento a la izquierda
            .padding(end = 16.dp), // Espacio con el centro
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,

        ) {
        // Flecha hacia la izquierda
        Icon(
            imageVector = icon,
            contentDescription = "Flecha hacia la izquierda",
            modifier = Modifier
                .size(24.dp)
                .graphicsLayer(rotationY = 180f) // Rotar la flecha para que apunte hacia la izquierda
                .padding(start = 6.dp), // Espacio entre la flecha y el texto
            tint = Color.White
        )
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
fun SlidingTextToRight(
    text: String,
    icon: ImageVector
) {
    val infiniteTransition = rememberInfiniteTransition()

    // Animar desplazamiento hacia la derecha
    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 25f, // Moverse 30dp hacia la derecha
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000), // Ciclo de 800ms
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Row(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .offset(x = offsetX.dp) // Aplicar desplazamiento a la derecha
            .padding(start = 16.dp), // Espacio con el centro
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.padding(end = 8.dp), // Espacio entre texto y flecha

        )
        // Flecha hacia la derech
        Icon(
            imageVector = icon,
            contentDescription = "Flecha hacia la derecha",
            modifier = Modifier
                .size(24.dp)
                .graphicsLayer(rotationY = 0f), // Flecha apuntando hacia la derecha

            tint = Color.White
        )
    }
}