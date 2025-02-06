import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SmokingRooms
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import com.example.hotelperemaria.R
import com.example.hotelperemaria.search_room.views.booking.BookRoomViewModel
import com.example.hotelperemaria.ui.theme.darkGray
import com.example.hotelperemaria.ui.theme.white

@Composable
fun BookingDetailsView(viewModel: BookRoomViewModel) {
    val bookRoomState by viewModel.bookRoomState.collectAsState()

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
            text ="Detalles de la Reserva",
            color = white,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)

        )

        BookingDetailItem(
            label = "Fecha de Entrada",
            value = bookRoomState.startDate,
            icon = Icons.Default.CalendarMonth
        )

        BookingDetailItem(
            label = "Fecha de Salida",
            value = bookRoomState.endDate,
            icon = Icons.Default.CalendarMonth
        )

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
        Spacer(modifier = Modifier.height(20.dp))
        ButtonCustom (modifier = Modifier.width(800.dp),
            colors =  ButtonDefaults.buttonColors(
                containerColor = Color(0xFF11147c), // Fondo transparente para usar el gradiente
                contentColor = white // Color del texto
            ),
            onClick = {}
        )
    }
}

@Composable
fun BookingDetailItem(label: String, value: String, icon: ImageVector) {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)

    ) {
        Text(
            text = label,
            color = white,
            fontSize = 22.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)

        )

        TextField(
            shape = RoundedCornerShape(12.dp),

            value = value,
            enabled = false,
            onValueChange = {},
            readOnly = true,
            textStyle = TextStyle(
                fontSize = 18.sp, textAlign = TextAlign.Center, color = white
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


@Preview
@Composable
fun previewpageComo() {
    BookingDetailsView(viewModel = BookRoomViewModel(SavedStateHandle()))
}
