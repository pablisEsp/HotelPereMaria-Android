package com.example.hotelperemaria.api

import com.example.hotelperemaria.CodigoResponse
import com.example.hotelperemaria.Reserva
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.rooms.model.HabitacionWrapper
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/rooms/{codigo}")
    suspend fun getHabitacionById(@Path("codigo") id: String): Habitacion

    @GET("api/rooms/unique") // Endpoint que devuelve una habitación por categoría
    suspend fun getUniqueRooms(): List<HabitacionWrapper>

    @GET("api/rooms") // Asegúrate de que este endpoint devuelve una lista de habitaciones
    suspend fun getHabitaciones(): List<Habitacion>




    //Calls to the bookings side

    @POST("api/bookings/getFreeRooms") // Se agregaron @Query para pasar los parámetros correctamente
    suspend fun getFreeRooms(
        @Query("startdate") startDate: String,
        @Query("enddate") endDate: String,
        @Query("numGuest") numGuest: Int
    ): List<Habitacion>

    @GET("api/bookings/getCode")
    suspend fun getCode(): CodigoResponse

    @POST("api/bookings/createBooking")
    suspend fun createBooking(
        @Body reserva : Reserva
    ): Boolean


}

