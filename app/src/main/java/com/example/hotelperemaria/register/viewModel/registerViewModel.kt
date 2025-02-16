package com.example.hotelperemaria.register.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelperemaria.api.RegisterRequest
import com.example.hotelperemaria.api.RetrofitInstance
import com.example.hotelperemaria.login.model.Usuario
import com.example.hotelperemaria.login.viewModel.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun register(
        nombre: String,
        email: String,
        apellido1: String,
        apellido2: String,
        dniPasaporte: String,
        movil: String,
        password: String,
        fechaNacimiento: String
    ) {
        viewModelScope.launch {
            try {
                _registerState.value = RegisterState.Loading

                val response = RetrofitInstance.api.register(
                    RegisterRequest(
                        nombre, email, apellido1, apellido2, dniPasaporte, movil, password, fechaNacimiento
                    )
                )

                if (response.isSuccessful) {
                    val user = response.body()?.data
                    if (user != null) {
                        _registerState.value = RegisterState.Success(user)
                    } else {
                        _registerState.value = RegisterState.Error("Error al registrar usuario")
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Error desconocido"
                    _registerState.value = RegisterState.Error(errorBody)
                }
            } catch (e: Exception) {
                Log.e("REGISTER_ERROR", "Error en registro: ${e.message}")
                _registerState.value = RegisterState.Error("Error al registrar usuario")
            }
        }
    }
}

// Estados del Registro
sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val usuario: Usuario) : RegisterState()
    data class Error(val message: String) : RegisterState()
}