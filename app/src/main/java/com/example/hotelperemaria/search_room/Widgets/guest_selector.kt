import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GuestSelector(
    modifier: Modifier = Modifier,
    onGuestCountChanged: (Int) -> Unit
) {
    var guestCount by remember { mutableIntStateOf(1) } // Estado para el nÃºmero de huÃ©spedes

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()), // Scroll horizontal
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // BotÃ³n para disminuir el nÃºmero de huÃ©spedes
        IconButton(
            onClick = {
                if (guestCount > 1) {
                    guestCount--
                    onGuestCountChanged(guestCount)
                }
            },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Disminuir huespedes",
                tint = Color(0xFF36454F),
            )
        }

        // Mostrar el nÃºmero de huÃ©spedes
        Text(
            text = "$guestCount",
            color = Color(0xFF36454F),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // BotÃ³n para aumentar el nÃºmero de huÃ©spedes
        IconButton(
            onClick = {
                if (guestCount < 6) {
                    guestCount++
                    onGuestCountChanged(guestCount)
                }
            },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Aumentar huespedes",
                tint = Color(0xFF36454F),

            )
        }
    }
}