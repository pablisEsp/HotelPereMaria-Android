package com.example.hotelperemaria.login.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelperemaria.api.ApiService
import com.example.hotelperemaria.api.LoginRequest
import com.example.hotelperemaria.api.RetrofitInstance
import com.example.hotelperemaria.login.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loginState.value = LoginState.Loading

                val response = RetrofitInstance.api.login(LoginRequest(email, password))

                if (response.isSuccessful) {
                    val token = response.body()?.data?.token
                    if (!token.isNullOrEmpty()) {
                        _loginState.value = LoginState.Success(token)
                    } else {
                        _loginState.value = LoginState.Error("Token no recibido")
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Error desconocido"
                    _loginState.value = LoginState.Error(errorBody)
                }
            } catch (e: Exception) {
                Log.e("LOGIN_ERROR", "Error en login: ${e.message}")
                _loginState.value = LoginState.Error("Error al iniciar sesión")
            }
        }
    }
}

// Estados del Login
sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
}