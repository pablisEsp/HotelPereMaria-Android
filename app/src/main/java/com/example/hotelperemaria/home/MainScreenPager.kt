
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.hotelperemaria.home.widgets.MainHomeScreen
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel

@Composable
fun MainScreenPager(
    habitaciones: List<Habitacion>,
    navController: NavController,
    viewModelBookRoom: BookRoomViewModel
) {

    // Estado para controlar la visibilidad del tutorial
    val totalPages = habitaciones.size + 2
    val pagerState = rememberPagerState(pageCount = { totalPages }, initialPage = 1)


    Box(modifier = Modifier.fillMaxSize()) {
        // Pantalla principal con el pager
        MainHomeScreen(
            habitaciones = habitaciones,
            pagerState = pagerState,
            navController = navController,
            viewModelBookRoom
        )
    }
}


