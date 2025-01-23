import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.hotelperemaria.R
import com.example.hotelperemaria.home.model.Habitacion
import kotlin.math.absoluteValue

@Composable
fun PantallaInicio(habitaciones: List<Habitacion>) {
    val totalPages = habitaciones.size + 1 // HACER CONSULTA NUMERO HAB + 1 (INICIAL)
    val pagerState = rememberPagerState(pageCount = { totalPages }) // Inicializa en la primera página
    val coroutineScope = rememberCoroutineScope() // Para manejar animaciones

    Box(modifier = Modifier.fillMaxSize()) {
        // HorizontalPager para habitaciones
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Card(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        // Efecto de animación para cada página
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        // Ajustar opacidad
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                if (page == 0) {
                    // Primera página: Icono del hotel
                    PrimeraPagina()
                } else {
                    // Páginas de habitaciones
                    val habitacion = habitaciones[page - 1] // Ajustamos el índice
                    HabitacionPage(habitacion = habitacion)
                }
            }
        }

        // Indicadores de página (Dots)
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
                        .size(if (pagerState.currentPage == iteration) 12.dp else 8.dp) // Tamaño dinámico
                )
            }
        }
    }
}

@Composable
fun PrimeraPagina() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen del icono del hotel
        Image(
            painter = painterResource(id = R.drawable.asgard_icon),
            contentDescription = "Icono del Hotel Asgard",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp) // Margen opcional para centrar el icono
        )
    }
}

@Composable
fun HabitacionPage(habitacion: Habitacion) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = habitacion.imagen),
            contentDescription = "Imagen de fondo de la habitación",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Nombre de la habitación
        Text(
            text = habitacion.nombre,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
