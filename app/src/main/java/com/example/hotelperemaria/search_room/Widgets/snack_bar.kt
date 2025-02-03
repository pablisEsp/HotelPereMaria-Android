package com.example.hotelperemaria.search_room.Widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@SuppressLint("RememberReturnType")
@Composable
fun SnackbarCustom(
    message: String,
    isShown: Boolean,
    hideSnackBar: () -> Unit,
    snackbarHostState : SnackbarHostState
) {

    var showSnackbar = remember { mutableStateOf(isShown) } // Estado para controlar el Snackbar

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        // SnackbarHost
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

    // Mostrar el Snackbar cuando showSnackbar sea true
    if (showSnackbar.value) {
        LaunchedEffect(Unit) {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
            delay(3000) // Espera 3 segundos
            hideSnackBar
        }
    }
}