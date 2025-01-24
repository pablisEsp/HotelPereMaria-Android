import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.example.hotelperemaria.R
import com.example.hotelperemaria.rooms.model.Habitacion
import kotlin.math.absoluteValue

@Composable
fun PantallaInicioConTutorial(habitaciones: List<Habitacion>, navController: NavController) {
    // Estado para controlar la visibilidad del tutorial
    var showTutorial by rememberSaveable { mutableStateOf(true) }
    val totalPages = habitaciones.size + 1
    val pagerState = rememberPagerState(pageCount = { totalPages }) // Estado del HorizontalPager

    // Detectar si el usuario deslizó hacia la derecha
    if (showTutorial && pagerState.currentPage > 0) {
        showTutorial = false // Ocultar el tutorial automáticamente si desliza
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Pantalla principal con el pager
        PantallaInicio(habitaciones = habitaciones, pagerState = pagerState, navController = navController)

        // Mostrar el tutorial si está activo
        TutorialDesliza(showTutorial = showTutorial) {
            showTutorial = false // Ocultar el tutorial al tocar "Entendido"
        }
    }
}



@Composable
fun PantallaInicio(habitaciones: List<Habitacion>, pagerState: androidx.compose.foundation.pager.PagerState, navController: NavController) {
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Card(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                if (page == 0) {
                    PrimeraPagina()
                } else {
                    val habitacion = habitaciones[page - 1]
                    HabitacionPage(habitacion = habitacion, navController = navController)
                }
            }
        }

        // Texto para indicar "Desliza" en la primera página
        if (pagerState.currentPage == 0) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward, // Flecha hacia la derecha
                    contentDescription = "Desliza hacia la derecha",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(bottom = 4.dp), // Espaciado entre flecha y texto
                    tint = Color.White
                )
                Text(
                    text = "Desliza para explorar",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

        // Indicadores de página (dots)
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(if (pagerState.currentPage == iteration) 12.dp else 8.dp)
                )
            }
        }
    }
}


@Composable
fun PrimeraPagina() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Imagen del icono del hotel
        Image(
            painter = painterResource(id = R.drawable.asgard_icon),
            contentDescription = "Icono del Hotel Asgard",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 100.dp) // Reduce el espacio entre la imagen y el texto
        )

        // Título y descripción
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Descubre la belleza nórdica",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Explora nuestras habitaciones únicas y disfruta de una experiencia inolvidable en el corazón del norte.",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp),
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun HabitacionPage(habitacion: Habitacion, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = habitacion.imagen),
            contentDescription = "Imagen de fondo de la habitación",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Nombre de la habitación y botón
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = habitacion.nombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextButton(
                onClick = {
                    // Navegar a la pantalla de detalles con el ID de la habitación
                    navController.navigate("room_screen/${habitacion.id}")
                },
                modifier = Modifier
                    .background(Color.White, shape = CircleShape)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Ver Detalles",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
        }
    }
}



@Composable
fun TutorialDesliza(showTutorial: Boolean, onDismiss: () -> Unit) {
    if (showTutorial) {
        // Superficie oscura semitransparente
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f)) // Fondo oscuro con transparencia
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Texto explicativo
                Text(
                    text = "Desliza hacia la derecha para explorar nuestras habitaciones",
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )

                // Flecha animada o estática
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Flecha para deslizar",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(top = 8.dp)
                )

                // Botón para cerrar el tutorial
                Text(
                    text = "Entendido",
                    color = Color.Cyan,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { onDismiss() } // Dismiss el tutorial
                )
            }
        }
    }
}
