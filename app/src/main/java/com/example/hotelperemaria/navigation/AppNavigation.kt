package com.example.hotelperemaria.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hotelperemaria.home.HomeScreen
import com.example.hotelperemaria.rooms.view.RoomDetailScreen
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
            //lele
        }


        // Ruta para la pantalla de detalles de la habitación
        composable(
            route = "room_screen/{roomId}",
            arguments = listOf(navArgument("roomId") { type = NavType.StringType }) // ✅ Cambiado a StringType
        ) { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("roomId") // ✅ Asegurar que es un String

            if (roomId != null) {
                RoomDetailScreen(roomId, navController) // ✅ Evita que se pase null
            } else {
                Text("Error: Código de habitación no encontrado", color = Color.Red)
            }
        }


    }
}