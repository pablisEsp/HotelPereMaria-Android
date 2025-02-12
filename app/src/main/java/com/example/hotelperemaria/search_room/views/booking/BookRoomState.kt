package com.example.hotelperemaria.search_room.views.booking

import com.example.hotelperemaria.Reserva
import com.example.hotelperemaria.rooms.model.Habitacion


data class BookRoomState(
    val startDate: String = "",
    val endDate: String = "",
    val numberOfGuests: Int = 1,
    val snackBarIsShown: Boolean = false,
    val snackBarMessage: String = "",
    val listOfRoomBooks : List<Habitacion> = listOf(),
    val selectedRoom : Habitacion? = null,
    val totalPrice :Double = 0.0,
    val bookingCode:String= "",
    val userId:String = "67aa1964acc7f4ca632c6f78",
    val booking: Reserva? = null,
)