import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SmokingRooms
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hotelperemaria.R
import com.example.hotelperemaria.navigation.AppScreens
import com.example.hotelperemaria.search_room.views.booking.BookRoomEvent
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel
import com.example.hotelperemaria.ui.theme.darkGray
import com.example.hotelperemaria.ui.theme.white
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookingDetailsView(
    navController:NavController,
    viewModel: BookRoomViewModel) {
    val bookRoomState by viewModel.bookRoomState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(

    ) { _ ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.fondoreservas),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp) // Espaciado uniforme entre elementos
        ) {

            Text(
                text = "Detalles de la Reserva",
                color = white,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(intrinsicSize = IntrinsicSize.Max),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DateDetailItem(
                    label = "Fecha Entrada",
                    value = bookRoomState.startDate,
                    icon = Icons.Default.CalendarMonth,
                    modifier = Modifier.width(169.dp)

                )

                DateDetailItem(
                    label = "Fecha Salida",
                    value = bookRoomState.endDate,
                    icon = Icons.Default.CalendarMonth,
                    modifier = Modifier.width(169.dp)
                )
            }



            BookingDetailItem(
                label = "Número de Huéspedes",
                value = bookRoomState.numberOfGuests.toString(),
                icon = Icons.Default.Person,
            )

            BookingDetailItem(
                label = "Habitacion Seleccionada",
                value = bookRoomState.selectedRoom!!.nombre,
                icon = Icons.Default.SmokingRooms,
            )
            BookingDetailItem(
                label = "Precio Final",
                value = bookRoomState.totalPrice.toString(),
                icon = Icons.Default.MonetizationOn
            )

            ButtonCustom(modifier = Modifier.width(800.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF191936), // Fondo transparente para usar el gradiente
                    contentColor = white // Color del texto
                ),
                onClick = {

                    viewModel.onEvent(BookRoomEvent.CreateBooking)
                    scope.launch {
                        delay(timeMillis = 1100L)
                        navController.navigate(AppScreens.homeScreen.route)

                    }
                }
            )

        }
    }


}

@Composable
fun BookingDetailItem(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)

    ) {
        Text(
            text = label,
            color = white,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally).padding(5.dp)

        )

        TextField(
            shape = RoundedCornerShape(12.dp),

            value = value,
            enabled = false,
            onValueChange = {},
            readOnly = true,
            textStyle = TextStyle(
                fontSize = 22.sp, textAlign = TextAlign.Center, color = white
            ),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = darkGray.copy(alpha = 0.8f),
                disabledTextColor = white,
                disabledIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = icon, contentDescription = null, tint = white)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp) // Más compacto
        )
    }
}

@Composable
fun DateDetailItem(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
        .width(172.dp)
        .height(60.dp),
    ) {
    Column {
    Text(
        text = label,
        color = white,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
    )
        Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = modifier.fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = darkGray.copy(alpha = 0.6f)), // Primero background
            //.border(2.dp, Color.White, shape = RoundedCornerShape(30.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        TextField(
            shape = RoundedCornerShape(30.dp),
            value = value,
            enabled = false,
            onValueChange = {},
            readOnly = true,
            textStyle = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = white,
                fontWeight = FontWeight.Bold
            ),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                disabledTextColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    tint = white,
                    contentDescription = null,
                    )

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp) // Más compacto
        )
    }}
}

@Preview
@Composable
fun FechaPreview() {
    DateDetailItem("Fecha Entrada", "12/2/2024", Icons.Default.CalendarMonth)
}