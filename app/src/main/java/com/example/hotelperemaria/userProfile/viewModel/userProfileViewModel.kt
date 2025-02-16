package com.example.hotelperemaria.userProfile.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelperemaria.api.ProfileRequest
import com.example.hotelperemaria.api.RetrofitInstance
import com.example.hotelperemaria.login.model.Usuario
import com.example.hotelperemaria.utils.Config
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {

    private val apiService = RetrofitInstance.api
    private val _userProfile = MutableStateFlow<Usuario?>(null)
    val userProfile: StateFlow<Usuario?> get() = _userProfile

    init {
        viewModelScope.launch {
            getUserProfile()
        }
    }

    private suspend fun getUserProfile() {

        Log.d("Email de prueba", Config.email_user)
        try {
            val response = apiService.getUserProfile(Config.email_user)
            _userProfile.value = response
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}