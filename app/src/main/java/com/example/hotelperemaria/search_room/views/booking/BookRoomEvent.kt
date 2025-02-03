package com.example.hotelperemaria.search_room.views.booking

sealed class BookRoomEvent {
    data class AddStartDate(val value: String): BookRoomEvent()
    data class AddEndDate(val value: String): BookRoomEvent()
    data class AddMoreGuest(val value:Int): BookRoomEvent()
    data class QuitGuest(val value: Int): BookRoomEvent()
    data class ShowSnackBar(val value: String): BookRoomEvent()
    data class HideSnackBar(val value: Boolean): BookRoomEvent()
    data object SearchRooms: BookRoomEvent()
}