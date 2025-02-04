@file:Suppress("UNREACHABLE_CODE")

package com.example.hotelperemaria.navigation

import BookRoomsScreen
import RoomDetailScreen
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hotelperemaria.home.HomeScreen
import com.example.hotelperemaria.search_room.views.ChoseRoomScreen
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel

@Composable
fun AppNavigation() {
    val viewModelBookRoom : BookRoomViewModel = hiltViewModel()
    val bookRoomState by viewModelBookRoom.bookRoomState.collectAsState()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.homeScreen.route) {
        // home view
        composable(route = AppScreens.homeScreen.route) {
            HomeScreen(navController, viewModelBookRoom = viewModelBookRoom)
        }

        // room search view
        composable(route = AppScreens.bookRoomsScreen.route) {
            BookRoomsScreen(navController = navController, viewModel = viewModelBookRoom)
            //lele
        }

        composable(route = AppScreens.choseRoomScreen.route) {
            ChoseRoomScreen(
                navController = navController,
                onRoomClick = { Room -> Unit},
                modifier = Modifier,
                scrollState = rememberLazyListState() ,
                viewModel = viewModelBookRoom)}



        // Ruta para la pantalla de detalles de la habitación
        composable(
            route = "room_screen/{codigo}",
            arguments = listOf(navArgument("codigo") { type = NavType.StringType })
        ) { backStackEntry ->
            val codigo = backStackEntry.arguments?.getString("codigo")

            if (codigo != null) {
                RoomDetailScreen(codigo, navController)
            } else {
                Text("Error: Código de habitación no encontrado", color = Color.Red)
            }
        }


    }
}