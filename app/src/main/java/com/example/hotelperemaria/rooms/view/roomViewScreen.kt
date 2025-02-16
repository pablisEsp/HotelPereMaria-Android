import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.hotelperemaria.navigation.AppScreens
import com.example.hotelperemaria.rooms.model.Servicio
import com.example.hotelperemaria.rooms.model.getPainterFromName
import com.example.hotelperemaria.rooms.model.mapServicios
import com.example.hotelperemaria.rooms.viewmodel.RoomViewModel
import com.example.hotelperemaria.ui.theme.lightBlack
import com.example.hotelperemaria.ui.theme.silver
import com.example.hotelperemaria.ui.theme.white
import com.example.hotelperemaria.utils.Config.SERVER_IP
import kotlinx.coroutines.launch

@Composable
fun RoomDetailScreen(
    idHabitacion: String,
    navController: NavController,
    roomViewModel: RoomViewModel = hiltViewModel() // 🔥 Usamos Hilt para inyectar el ViewModel

) {
    val habitacion by roomViewModel.habitacion.collectAsState()
    val isLoading by roomViewModel.isLoading.collectAsState()
    val coroutineScope = rememberCoroutineScope() // Necesario para lanzar animaciones


    LaunchedEffect(idHabitacion) {
        roomViewModel.fetchHabitacion(idHabitacion)
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (habitacion == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Habitación no encontrada", color = Color.Red, fontSize = 18.sp)
        }
        return
    }

    val servicios = mapServicios(habitacion!!.serviciosString)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBlack)
    ) {
        ImageCarousel(images = habitacion!!.imagenes)

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Descripción",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = white
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = habitacion!!.descripcion,
                fontSize = 14.sp,
                color = white
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Servicios", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = white)
            Spacer(modifier = Modifier.height(8.dp))
            RoomServices(services = servicios)
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${habitacion!!.precio}€ / noche",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = white
                )
                Button(
                    onClick = {
                        // Guardamos el valor 0 para que la pantalla anterior sepa a qué página ir
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("selectedPage", 0)
                        // Regresamos a la pantalla anterior
                        navController.popBackStack()
                    },
                    shape = CircleShape
                ) {
                    Text(text = "Reservar ahora", fontSize = 18.sp, color = white)
                }


            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RoomServices(services: List<Servicio>) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        services.forEach { servicio ->
            Box(
                modifier = Modifier
                    .widthIn(min = 80.dp, max = 140.dp)
                    .heightIn(min = 0.dp, max = 60.dp)
                    .border(2.dp, silver, CircleShape)
                    .background(Color.White, shape = CircleShape) // Fondo blanco para contraste
                    .padding(horizontal = 10.dp, vertical = 6.dp) // Espaciado interno
                    .padding(4.dp)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = getPainterFromName(servicio.icono), // Usar `painterResource`
                        contentDescription = servicio.nombre,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = servicio.nombre,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        overflow = TextOverflow.Ellipsis,
                        color = lightBlack
                    )
                }
            }
        }
    }
}

@Composable
fun ImageCarousel(images: List<String>) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(top = 20.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(16.dp)) // Aplica esquinas redondeadas

    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            AsyncImage(
                model = SERVER_IP + images[page],
                contentDescription = "Imagen de la habitación",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center

        ) {
            repeat(images.size) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            if (index == pagerState.currentPage) white else Color.Gray,
                            shape = CircleShape
                        )
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}
