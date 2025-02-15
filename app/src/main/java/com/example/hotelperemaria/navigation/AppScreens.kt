package com.example.hotelperemaria.navigation

sealed class AppScreens(val route: String) {

    data object bookRoomsScreen: AppScreens("book_rooms_screen")

//    data object FirstScreen: AppScreens("first_screen")
//    data object SecondScreen: AppScreens("second_screen")
    data object homeScreen: AppScreens("home_screen")

    data object RoomScreen: AppScreens("room_screen")

    data object choseRoomScreen:AppScreens("chose_room_screen")

    data object viewDetailsBooking :AppScreens("details_booking")

    data object loginViewScreen :AppScreens("loginScreen")

    data object loginToRegister :AppScreens("loginToRegister")

    data object regiserViewScreen :AppScreens("registerScreen")

}
//this are simulated screens , here should be the reals ones, those are examples for you to replace with yours screens