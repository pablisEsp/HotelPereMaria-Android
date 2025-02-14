package com.example.hotelperemaria.api

import com.example.hotelperemaria.login.model.UsuarioWrapper
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.rooms.model.HabitacionWrapper
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Objects

interface ApiService {
    @GET("api/rooms/{codigo}")
    suspend fun getHabitacionById(@Path("codigo") id: String): Habitacion

    @GET("api/rooms/unique") // Endpoint que devuelve una habitación por categoría
    suspend fun getUniqueRooms(): List<HabitacionWrapper>

    @GET("api/rooms") // Asegúrate de que este endpoint devuelve una lista de habitaciones
    suspend fun getHabitaciones(): List<Habitacion>

    @POST("api/users/login")
    suspend fun login(@Body request: LoginRequest): UsuarioWrapper

    data class LoginRequest(
        val email: String,
        val password: String
    )


    //Calls to th

    @POST("api/bookings/getFreeRooms") // Se agregaron @Query para pasar los parámetros correctamente
    suspend fun getFreeRooms(
        @Query("startdate") startDate: String,
        @Query("enddate") endDate: String,
        @Query("numGuest") numGuest: Int
    ): List<Habitacion>

}

