package com.example.hotelperemaria.search_room.views.booking

import java.time.LocalDate


data class BookRoomState(
    val startDate: String = "",
    val endDate: String = "",
    val numberOfGuests: Int = 1,
    val snackBarIsShown: Boolean = false,
    val snackBarMessage: String = "",
)