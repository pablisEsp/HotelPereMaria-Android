package com.example.hotelperemaria


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hotelperemaria.navigation.AppNavigation
import com.example.hotelperemaria.ui.theme.HotelPereMariaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HotelPereMariaTheme {
                AppNavigation()

            }
        }
    }
}

