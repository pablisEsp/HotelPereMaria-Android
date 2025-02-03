import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotelperemaria.R
import com.example.hotelperemaria.search_room.views.booking.BookRoomEvent
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel
import com.example.hotelperemaria.search_room.views.widgets.DatePickerDialogCustom

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookRoomsScreen(
    navController: NavController,
    viewModel: BookRoomViewModel = hiltViewModel(),
) {
    val bookRoomState by viewModel.bookRoomState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { _ -> // Ignoramos el padding porque ya no lo usamos
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Fondo de pantalla
            Image(
                painter = painterResource(id = R.drawable.fondoreservas),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Contenido encima del fondo
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), // Puedes eliminarlo si quieres
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo o imagen visible
                Image(
                    painter = painterResource(R.drawable.asgard_easter_egg_dorado),
                    contentDescription = "Logo"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Start Date Picker
                DatePickerDialogCustom(
                    modifier = Modifier,
                    initialDate = bookRoomState.startDate,
                    onDateSelected = {
                        viewModel.onEvent(BookRoomEvent.AddStartDate(it))
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // End Date Picker
                DatePickerDialogCustom(
                    modifier = Modifier,
                    initialDate = bookRoomState.endDate,
                    onDateSelected = {
                        viewModel.onEvent(BookRoomEvent.AddEndDate(it))
                    }
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
                    // navController.navigate(AppScreens.choseRoomScreen.route)
                })

                SnackbarCustom(
                    isShown = bookRoomState.snackBarIsShown,
                    message = bookRoomState.snackBarMessage,
                    hideSnackBar = { viewModel.onEvent(BookRoomEvent.HideSnackBar(bookRoomState.snackBarIsShown)) } ,
                    snackbarHostState = snackbarHostState
                )
            }
        }
    }
}

