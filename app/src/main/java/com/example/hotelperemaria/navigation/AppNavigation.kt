package com.example.hotelperemaria.navigation

import BookRoomsScreen
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hotelperemaria.home.HomeScreen
import com.example.hotelperemaria.home.habitaciones
import com.example.hotelperemaria.rooms.view.RoomScreen

import com.example.hotelperemaria.search_room.views.ChoseRoomScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.homeScreen.route) {
        composable(route = AppScreens.homeScreen.route) {
            HomeScreen(navController)
        }

        // room search view
        composable(route = AppScreens.bookRoomsScreen.route) {
            BookRoomsScreen(
                navController = navController,
            )

        }

        composable(route = AppScreens.choseRoomScreen.route) {
            ChoseRoomScreen(
                habitaciones = habitaciones, navController = navController,
                onRoomClick = { Room -> Unit },
                modifier = Modifier,
                scrollState = rememberLazyListState() )
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
