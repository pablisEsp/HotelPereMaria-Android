package com.example.hotelperemaria.search_room.views.booking

import com.example.hotelperemaria.Reserva
import com.example.hotelperemaria.rooms.model.Habitacion

sealed class BookRoomEvent {
    data class AddStartDate(val value: String): BookRoomEvent()
    data class AddEndDate(val value: String): BookRoomEvent()
    data class AddMoreGuest(val value:Int): BookRoomEvent()
    data class QuitGuest(val value: Int): BookRoomEvent()
    data class ShowSnackBar(val value: String): BookRoomEvent()
    data class HideSnackBar(val value: Boolean): BookRoomEvent()
    data object SearchRooms: BookRoomEvent()
    data class SelectRoom(val value: Habitacion) : BookRoomEvent()
    data object CreateBooking :BookRoomEvent()
}