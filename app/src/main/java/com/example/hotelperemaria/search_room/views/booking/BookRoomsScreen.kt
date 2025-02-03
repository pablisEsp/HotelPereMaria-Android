import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotelperemaria.R
import com.example.hotelperemaria.navigation.AppScreens
import com.example.hotelperemaria.search_room.views.booking.BookRoomEvent
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel
import com.example.hotelperemaria.search_room.views.widgets.DatePickerDialogCustom

@Composable
fun BookRoomsScreen(
    navController: NavController,
    viewModel: BookRoomViewModel = hiltViewModel(),
) {
    val bookRoomState by viewModel.bookRoomState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
// Fondo de pantalla
        Image(
            painter = painterResource(id = R.drawable.fondoreservas),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.asgard_easter_egg_dorado),
                contentDescription = null
            )
            // Start Date Picker

            DatePickerDialogCustom(
                modifier = Modifier,
                initialDate = bookRoomState.startDate,
                onDateSelected = {
                    viewModel.onEvent(BookRoomEvent.AddStartDate(it))
                },
                isStartDate = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // End Date Picker
            DatePickerDialogCustom(
                modifier = Modifier,
                initialDate = bookRoomState.startDate,
                onDateSelected = {
                    viewModel.onEvent(BookRoomEvent.AddEndDate(it))
                },
                isStartDate = false
            )


            Spacer(modifier = Modifier.height(16.dp))

            // Guest Selector
            GuestSelector(
                modifier = Modifier.fillMaxWidth(),
                onGuestCountChanged = { count ->
                    if (count > bookRoomState.numberOfGuests) {
                        viewModel.onEvent(BookRoomEvent.AddMoreGuest(count - bookRoomState.numberOfGuests))
                    } else {
                        viewModel.onEvent(BookRoomEvent.QuitGuest(bookRoomState.numberOfGuests - count))
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonCustom(onClick = {
                viewModel.onEvent(BookRoomEvent.SearchRooms)
                navController.navigate(AppScreens.choseRoomScreen.route)
            })
        }
    }
}
// Ejemplo de función para mostrar un mensaje de error
fun showSnackbar(message: String) {
    // Implementa aquí la lógica para mostrar un Snackbar o Toast
}