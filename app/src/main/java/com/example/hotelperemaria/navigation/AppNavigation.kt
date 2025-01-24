package com.example.hotelperemaria.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hotelperemaria.home.HomeScreen
import com.example.hotelperemaria.navigation.AppScreens
import com.example.hotelperemaria.rooms.view.RoomScreen
import com.example.hotelperemaria.search_room.screens.BookRoomsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.homeScreen.route) {
        // home view
        composable(route = AppScreens.homeScreen.route) {
            HomeScreen(navController)
        }

        // room search view
        composable(route = AppScreens.BookRoomsScreen.route) {
            BookRoomsScreen(navController = navController)

        }



        // Ruta para la pantalla de detalles de la habitación
        composable(
            route = "room_screen/{roomId}",
            arguments = listOf(navArgument("roomId") { type = NavType.IntType })
        ) { backStackEntry ->
            val roomId = backStackEntry.arguments?.getInt("roomId")
            RoomScreen(roomId)
        }


    }
}