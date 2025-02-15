package com.example.hotelperemaria.home.widgets

import BookRoomsScreen
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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel
import kotlin.math.absoluteValue

@Composable
fun MainHomeScreen(
    habitaciones: List<Habitacion>,
    pagerState: PagerState,
    navController: NavController,
    viewModelBookRoom: BookRoomViewModel,
) {
    //val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),

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
                if (page == 0) {
                    BookRoomsScreen(navController, viewModelBookRoom)
                } else if (page == 1) {
                    Home()
                } else {
                    // ✅ Previene error de índice si `habitaciones` está vacío
                    if (habitaciones.isNotEmpty() && page - 2 != habitaciones.size) {
                        val habitacion = habitaciones[page - 2]
                        //println("Habitación: $habitacion")
                        HabitacionPage(habitacion = habitacion, navController = navController)
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

        // Indicadores de página (dots)
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
