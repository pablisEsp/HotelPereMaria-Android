package com.example.hotelperemaria.rooms.viewmodel

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelperemaria.api.RetrofitInstance
import com.example.hotelperemaria.rooms.model.Habitacion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _habitaciones = MutableStateFlow<List<Habitacion>>(emptyList()) // Estado de la lista de habitaciones
    val habitaciones: StateFlow<List<Habitacion>> = _habitaciones

    private val _habitacion = MutableStateFlow<Habitacion?>(null)
    val habitacion: StateFlow<Habitacion?> = _habitacion

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _uniqueRooms = MutableStateFlow<List<Habitacion>>(emptyList())
    val uniqueRooms: StateFlow<List<Habitacion>> = _uniqueRooms

    fun fetchHabitacion(id: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _habitacion.value = RetrofitInstance.api.getHabitacionById(id)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }



    fun fetchUniqueRooms() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = RetrofitInstance.api.getUniqueRooms()

                // 🔥 Extraer las habitaciones de los wrappers
                val habitaciones = response.map { it.habitacion }

                _uniqueRooms.value = habitaciones // Ahora esto sí debería funcionar correctamente

                Log.d("API_RESPONSE", "Habitaciones únicas recibidas: ${habitaciones.size}")
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al obtener habitaciones únicas", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchHabitaciones() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = RetrofitInstance.api.getHabitaciones()
                _habitaciones.value = response
                Log.d("API_RESPONSE", "Habitaciones recibidas: ${response.size}") // Log para depurar
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al obtener habitaciones", e) // Log de error
            } finally {
                _isLoading.value = false
            }
        }
    }

}

