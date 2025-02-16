import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.hotelperemaria.home.widgets.MainHomeScreen
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.rooms.viewmodel.RoomViewModel
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel

@Composable
fun MainScreenPager(
    habitaciones: List<Habitacion>,
    navController: NavController,
    viewModelBookRoom: BookRoomViewModel,
    viewModelRoom: RoomViewModel
) {

    // Estado para controlar la visibilidad del tutorial
    val totalPages = habitaciones.size + 2
    val pagerState = rememberPagerState(pageCount = { totalPages }, initialPage = 1)
    val coroutineScope = rememberCoroutineScope()

    // Observa cambios en el savedStateHandle para saber si se debe cambiar de página
    LaunchedEffect(key1 = navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            backStackEntry.savedStateHandle.get<Int>("selectedPage")?.let { page ->
                pagerState.animateScrollToPage(page)
                // Limpiamos el valor para no volver a dispararlo
                backStackEntry.savedStateHandle.remove<Int>("selectedPage")
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Pantalla principal con el pager
        MainHomeScreen(
            habitaciones = habitaciones,
            pagerState = pagerState,
            navController = navController,
            viewModelBookRoom,
            viewModelRoom
        )
    }
}
