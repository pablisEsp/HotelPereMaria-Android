package com.example.hotelperemaria.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


private val DarkColorScheme = darkColorScheme(
        primary = Color(0xFF6200EA),
        secondary = Color(0xFF03DAC5),
        background = Color(0xFFF6F6F6),
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EA),
    secondary = Color(0xFF03DAC5),
    background = Color(0xFFF6F6F6),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
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
       content = content,
       typography = Typography(
           bodyLarge = MaterialTheme.typography.bodyLarge.copy(
               fontSize = 18.sp
           )))
}