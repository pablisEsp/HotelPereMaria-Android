package com.example.hotelperemaria.search_room.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hotelperemaria.R
import com.example.hotelperemaria.navigation.AppScreens
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.search_room.Widgets.HotelReservationCard
import com.example.hotelperemaria.search_room.views.booking.BookRoomEvent
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel

@Composable
fun ChoseRoomScreen(
    navController: NavController,
    onRoomClick: (Habitacion) -> Unit,
    modifier: Modifier,
    viewModel: BookRoomViewModel,
    scrollState: LazyListState = rememberLazyListState()
    ) {
    val bookRoomState by viewModel.bookRoomState.collectAsState()
    val habitaciones = bookRoomState.listOfRoomBooks
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
        LazyColumn(
            modifier = modifier,
            state = scrollState,

            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = habitaciones, key = { it.codigo }) { room ->
                HotelReservationCard(habitacion = room, selectRoom = { selectedRoom ->
                    viewModel.onEvent(BookRoomEvent.SelectRoom(selectedRoom))
                    navController.navigate(AppScreens.viewDetailsBooking.route)
                })

            }

        }

    }

}