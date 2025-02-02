package com.example.hotelperemaria.api

import com.example.hotelperemaria.rooms.model.Habitacion
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/rooms/{id}")
    suspend fun getHabitacionById(@Path("id") id: Int): Habitacion
}