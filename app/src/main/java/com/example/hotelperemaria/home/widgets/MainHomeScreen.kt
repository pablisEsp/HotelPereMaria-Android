package com.example.hotelperemaria.home.widgets

import BookRoomsScreen
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.rooms.viewmodel.RoomViewModel
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@Composable
fun MainHomeScreen(
    habitaciones: List<Habitacion>,
    pagerState: PagerState,
    navController: NavController,
    viewModelBookRoom: BookRoomViewModel,
    viewModelRoom: RoomViewModel,
) {

    val coroutineScope = rememberCoroutineScope() // Definir el scope para corrutinas

    Box(modifier = Modifier.fillMaxSize()) {
        // Paginación
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Card(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                when (page) {
                    0 -> BookRoomsScreen(navController, viewModelBookRoom)
                    1 -> Home()
                    else -> {
                        if (habitaciones.isNotEmpty() && page - 2 < habitaciones.size) {
                            val habitacion = habitaciones[page - 2]
                            HabitacionPage(habitacion = habitacion, navController = navController, viewModelRoom, pagerState)
                        } else {
                            Text(
                                text = "Cargando habitaciones...",
                                modifier = Modifier.fillMaxSize(),
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        if (pagerState.currentPage > 0) {
            // Botón superior izquierdo para ir a la primera página
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        for (i in pagerState.currentPage downTo 0) {
                            pagerState.animateScrollToPage(i, animationSpec = tween(durationMillis = 150))
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 25.dp, end = 16.dp, start = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Book,
                    contentDescription = "Ir a la primera página",
                    tint = Color(0xFFffd78d),
                    modifier = Modifier.size(35.dp)
                )
            }
        }

        // Botón superior derecho
        IconButton(
            onClick = { /* Acción del botón derecho */ },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 25.dp, end = 16.dp, start = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.SupervisedUserCircle,
                contentDescription = null,
                tint = Color(0xFFffd78d),
                modifier = Modifier.size(35.dp)
            )
        }

        // Indicadores de página
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(if (pagerState.currentPage == iteration) 12.dp else 8.dp)
                )
            }
        }
    }
}
