import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@SuppressLint("RememberReturnType")
@Composable
fun SnackbarCustom(
    message: String,
    isShown: Boolean,
    hideSnackBar: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    if (isShown) {
        LaunchedEffect(Unit) {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
            delay(3000) // Espera 3 segundos
            hideSnackBar()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            snackbar = { data ->
                CustomSnackbar(data)
            }
        )
    }
}

@Composable
fun CustomSnackbar(snackbarData: SnackbarData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(Color(0xFF333333)) // Gris oscuro
            .padding(16.dp)
    ) {
        Text(
            text = snackbarData.visuals.message,
            color = Color.White
        )
    }
}
