package com.example.hotelperemaria.rooms.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelperemaria.api.RetrofitInstance
import com.example.hotelperemaria.rooms.model.Habitacion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {

    private val _habitacion = MutableStateFlow<Habitacion?>(null)
    val habitacion: StateFlow<Habitacion?> = _habitacion

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchHabitacion(id: Int) {
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
}
