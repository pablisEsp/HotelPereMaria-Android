package com.example.hotelperemaria.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkColorScheme = darkColorScheme(
    background = Color(0xFFF4F4F4),//whiteSmoke
    primary = Color(0xFFBCBCBC),//Silver
    tertiary = Color(0xFFFBFBFB), //White
     secondary = Color(0xFF3C3C3C), //Onix
    surface = Color(0xFFB0DAF1) //UranianBlue
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun HotelPereMariaTheme(
    content: @Composable () -> Unit
) {
   MaterialTheme(
       colorScheme = DarkColorScheme, // Usamos solo el tema oscuro
       content = content
   )
}