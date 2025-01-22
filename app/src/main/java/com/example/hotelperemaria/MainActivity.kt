   package com.example.hotelperemaria

import AppNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hotelperemaria.ui.theme.HotelPereMariaTheme

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

