@file:Suppress("UNREACHABLE_CODE")

package com.example.hotelperemaria.navigation

import BookRoomsScreen
import BookingDetailsView
import LoginScreen
import RoomDetailScreen
import SnackbarCustom
import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hotelperemaria.home.HomeScreen
import com.example.hotelperemaria.login.viewModel.LoginViewModel
import com.example.hotelperemaria.register.view.RegisterScreen
import com.example.hotelperemaria.rooms.viewmodel.RoomViewModel
import com.example.hotelperemaria.search_room.views.ChoseRoomScreen
import com.example.hotelperemaria.search_room.views.booking.BookRoomEvent
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel
import com.example.hotelperemaria.userProfile.view.UserProfileScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation() {
    val snackbarHostState = remember { SnackbarHostState() }
    val viewModelBookRoom: BookRoomViewModel = hiltViewModel()
    val bookRoomState by viewModelBookRoom.bookRoomState.collectAsState()
    val loginViewModel: LoginViewModel = hiltViewModel()
    val navController = rememberNavController()
    val roomViewModel: RoomViewModel = hiltViewModel()

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { _ ->

        NavHost(navController = navController, startDestination = AppScreens.loginViewScreen.route) {
            // home view
            composable(route = AppScreens.homeScreen.route) {
                HomeScreen(
                    navController,
                    viewModelBookRoom = viewModelBookRoom,
                    viewModelRoom = roomViewModel
                )
            }

            // room search view
            composable(route = AppScreens.bookRoomsScreen.route) {
                BookRoomsScreen(navController = navController, viewModel = viewModelBookRoom)
            }

            composable(route = AppScreens.choseRoomScreen.route) {
                ChoseRoomScreen(
                    navController = navController,
                    onRoomClick = { Room -> Unit },
                    modifier = Modifier,
                    scrollState = rememberLazyListState(),
                    viewModel = viewModelBookRoom
                )
            }
            composable(route = AppScreens.loginViewScreen.route) {
                LoginScreen(viewModel = loginViewModel, onLoginSuccess = {
                    navController.navigate(AppScreens.homeScreen.route)
                }, onRegisterClick = {
                    navController.navigate(AppScreens.regiserViewScreen.route) // ← Ahora sí navega al registro
                })
            }

            composable(route = AppScreens.regiserViewScreen.route) {
                RegisterScreen(onRegisterSuccess = {
                    navController.navigate(AppScreens.loginViewScreen.route)
                })
            }

            composable(route = AppScreens.profileViewScreen.route) {
                UserProfileScreen(onProfileSuccess = {
                    navController.navigate(AppScreens.homeScreen.route)
                })
            }

            composable(route = AppScreens.choseRoomScreen.route) {
                ChoseRoomScreen(
                    navController = navController,
                    onRoomClick = { Room -> Unit },
                    modifier = Modifier,
                    scrollState = rememberLazyListState(),
                    viewModel = viewModelBookRoom
                )
            }

            composable(route = AppScreens.viewDetailsBooking.route) {
                BookingDetailsView(navController, viewModelBookRoom)
            }


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
        SnackbarCustom(
            isShown = bookRoomState.snackBarIsShown,
            message = bookRoomState.snackBarMessage,
            hideSnackBar = { viewModelBookRoom.onEvent(BookRoomEvent.HideSnackBar(bookRoomState.snackBarIsShown)) },
            snackbarHostState = snackbarHostState
        )
    }


}

