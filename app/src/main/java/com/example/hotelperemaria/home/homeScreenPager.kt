import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
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
    val totalPages = habitaciones.size + 1
    val pagerState = rememberPagerState(pageCount = { totalPages }) // Estado del HorizontalPager


    Box(modifier = Modifier.fillMaxSize()) {
        // Pantalla principal con el pager
        PantallaInicio(habitaciones = habitaciones, pagerState = pagerState, navController = navController)
    }
}


@Composable
fun PantallaInicio(
    habitaciones: List<Habitacion>,
    pagerState: androidx.compose.foundation.pager.PagerState,
    navController: NavController
) {
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

        // Textos animados para "Desliza para explorar" y "Desliza para reservar"
        if (pagerState.currentPage == 0) {

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
                .padding(bottom = 16.dp, top = 100.dp)
        )

        // Título y descripción
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Descubre la belleza nórdica",
                textAlign = TextAlign.Center,
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
                lineHeight = 22.sp,
                textAlign = TextAlign.Center
            )
        }


        // Animaciones de deslizamiento
        SlidingSwipeText(
            text = "Desliza para reservar",
            icon = Icons.AutoMirrored.Filled.ArrowForward,
            alignment = Alignment.BottomStart, // Alineado a la izquierda
            iconRotation = 180f // Flecha apuntando hacia la izquierda
        )
        SlidingSwipeText(
            text = "Desliza para explorar",
            icon = Icons.AutoMirrored.Filled.ArrowForward,
            alignment = Alignment.BottomEnd, // Alineado a la derecha
            iconRotation = 0f // Flecha apuntando hacia la derecha
        )
    }
}

@Composable
fun SlidingSwipeText(
    text: String,
    icon: ImageVector,
    alignment: Alignment,
    iconRotation: Float // Rotación personalizada de la flecha
) {
    val infiniteTransition = rememberInfiniteTransition()

    // Animar desplazamiento horizontal (izquierda a derecha)
    val offsetX by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f, // Moverse 10dp hacia ambos lados
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800), // Ciclo de 800ms
            repeatMode = RepeatMode.Reverse
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .offset(x = offsetX.dp) // Aplicar el desplazamiento horizontal animado
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (alignment == Alignment.BottomStart) Arrangement.Start else Arrangement.End
    ) {
        if (alignment == Alignment.BottomStart) {
            // Flecha a la izquierda
            Icon(
                imageVector = icon,
                contentDescription = "Flecha hacia la izquierda",
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer(rotationY = iconRotation) // Aplicar rotación a la flecha
                    .padding(end = 8.dp), // Espacio entre la flecha y el texto
                tint = Color.White
            )
            Text(
                text = text,
                color = Color.White,
                fontSize = 12.sp
            )
        } else if (alignment == Alignment.BottomEnd) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.padding(end = 8.dp) // Espacio entre texto y flecha
            )
            Icon(
                imageVector = icon,
                contentDescription = "Flecha hacia la derecha",
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer(rotationY = iconRotation) // Aplicar rotación a la flecha
                    .padding(start = 8.dp), // Espacio entre texto y flecha
                tint = Color.White
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
