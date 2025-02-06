package com.example.hotelperemaria.utils

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.graphics.Color
import com.example.hotelperemaria.ui.theme.darkGray
import com.example.hotelperemaria.ui.theme.lightGray
import com.example.hotelperemaria.ui.theme.white
import com.example.hotelperemaria.ui.theme.whitesmoke

val colorsOutfield = TextFieldColors(
disabledContainerColor = darkGray.copy(alpha = 0.8f),
focusedTextColor = white,
unfocusedTextColor = white,
disabledTextColor = white,
errorTextColor = Color.Red, // Magenta
focusedContainerColor = lightGray.copy(alpha = 0.8f), // Naranja coral oscuro
unfocusedContainerColor = darkGray.copy(alpha = 0.8f), // Azul oscuro
errorContainerColor = Color.Magenta,
cursorColor = white,
errorCursorColor = Color.Red,
textSelectionColors = TextSelectionColors(
handleColor = white,
backgroundColor = white, // Fondo selección naranja coral
),
focusedIndicatorColor = white,
unfocusedIndicatorColor = white,
disabledIndicatorColor = white,
errorIndicatorColor = Color.Magenta,
focusedLeadingIconColor = white, // Blanco para consistencia con texto
unfocusedLeadingIconColor = white, // Gris claro
disabledLeadingIconColor = white, // Gris claro
errorLeadingIconColor = Color.Magenta, // Magenta para errores
focusedTrailingIconColor = white,
unfocusedTrailingIconColor = white,
disabledTrailingIconColor = white,
errorTrailingIconColor = Color.Magenta,
focusedLabelColor = Color.Black, // Naranja coral
unfocusedLabelColor = white,
disabledLabelColor = white,
errorLabelColor = Color.Magenta,
focusedPlaceholderColor = whitesmoke, // Naranja coral
unfocusedPlaceholderColor = whitesmoke,
disabledPlaceholderColor = whitesmoke,
errorPlaceholderColor = Color.Magenta,
focusedSupportingTextColor = white, // Texto blanco en estado enfocado
unfocusedSupportingTextColor = whitesmoke,
disabledSupportingTextColor = whitesmoke,
errorSupportingTextColor = Color.Magenta,
focusedPrefixColor = lightGray.copy(0.8f), // Naranja coral
unfocusedPrefixColor = whitesmoke,
disabledPrefixColor = whitesmoke,
errorPrefixColor = Color.Magenta,
focusedSuffixColor = lightGray, // Naranja coral
unfocusedSuffixColor = darkGray,
disabledSuffixColor = darkGray,
errorSuffixColor = Color.Magenta
)