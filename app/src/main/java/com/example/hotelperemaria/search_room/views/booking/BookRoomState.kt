package com.example.hotelperemaria.search_room.views.booking

import com.example.hotelperemaria.rooms.model.Habitacion
import java.time.LocalDate


data class BookRoomState(
    val startDate: String = "",
    val endDate: String = "",
    val numberOfGuests: Int = 1,
    val snackBarIsShown: Boolean = false,
    val snackBarMessage: String = "",
    val listOfRoomBooks : List<Habitacion> = listOf()
)