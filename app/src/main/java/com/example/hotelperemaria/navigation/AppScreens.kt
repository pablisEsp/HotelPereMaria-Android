package com.example.hotelperemaria.navigation

sealed class AppScreens(val route: String) {
    data object FirstScreen: AppScreens("first_screen")
    data object SecondScreen: AppScreens("second_screen")
    data object ThirdScreen: AppScreens("third_screen")
}
//this are simulated screens , here should be the reals ones, those are examples for you to replace with yours screens