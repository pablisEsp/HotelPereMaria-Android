import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
        LaunchedEffect(isShown) {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
            delay(1500) // Espera 1.5 segundos antes de ocultarlo
            hideSnackBar()
        }
    }
}


