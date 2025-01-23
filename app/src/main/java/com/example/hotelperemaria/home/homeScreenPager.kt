import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelperemaria.home.model.Habitacion

@Composable
fun PantallaInicio(habitaciones: List<Habitacion>) {
    val pagerState = rememberPagerState() // No necesita parámetros aquí.

    Box(modifier = Modifier.fillMaxSize()) {
        // Pager para mostrar habitaciones
        HorizontalPager(
            state = pagerState,
            pageCount = habitaciones.size, // Aquí se pasa el número de páginas
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val habitacion = habitaciones[page]
            HabitacionPage(habitacion = habitacion)
        }

        // Nombre del hotel (arriba)
        Text(
            text = "Hotel Pere Maria",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
@Composable
fun HabitacionPage(habitacion: Habitacion) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = habitacion.imagenResId),
            contentDescription = null,
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


