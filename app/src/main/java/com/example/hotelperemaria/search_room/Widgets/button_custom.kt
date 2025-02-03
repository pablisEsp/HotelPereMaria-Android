import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import com.example.hotelperemaria.ui.theme.white

@Composable
fun ButtonCustom(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 32.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.medium, // Bordes redondeados
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkGray, // Fondo transparente para usar el gradiente
            contentColor = white // Color del texto
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp, // Elevación por defecto
            pressedElevation = 8.dp // Elevación al presionar
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Reservar",
                style = MaterialTheme.typography.titleMedium,
                color = white
            )
        }
    }
}