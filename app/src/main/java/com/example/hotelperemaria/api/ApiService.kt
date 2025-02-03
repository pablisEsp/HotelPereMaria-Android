package com.example.hotelperemaria.api

import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.rooms.model.HabitacionWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/rooms/{codigo}")
    suspend fun getHabitacionById(@Path("codigo") id: String): Habitacion

    @GET("api/rooms/unique") // Endpoint que devuelve una habitación por categoría
    suspend fun getUniqueRooms(): List<HabitacionWrapper>

    @GET("api/rooms") // Asegúrate de que este endpoint devuelve una lista de habitaciones
    suspend fun getHabitaciones(): List<Habitacion>


}

