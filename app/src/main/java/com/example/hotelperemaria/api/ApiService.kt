package com.example.hotelperemaria.api

import com.example.hotelperemaria.login.model.Usuario
import com.example.hotelperemaria.rooms.model.Habitacion
import com.example.hotelperemaria.rooms.model.HabitacionWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// Almacenar datos para enviar a la api y comprobar credenciales
data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val error: String?, val data: TokenData?)
data class TokenData(val token: String)

data class ProfileRequest(
    val email: String
)

//Datos obtenidos del view que serán mandados a la api paraa posteriormente crear un nuevo usuario
data class RegisterRequest(
    val nombre: String,
    val email: String,
    val apellido1: String,
    val apellido2: String?,
    val dniPasaporte: String,
    val movil: String,
    val password: String,
    val fechaNacimiento: String
)



// Obtenemos la respuesta del registro
data class RegisterResponse(val error: String?, val data: Usuario?)

interface ApiService {
    @GET("api/rooms/{codigo}")
    suspend fun getHabitacionById(@Path("codigo") id: String): Habitacion

    @GET("api/rooms/unique") // Endpoint que devuelve una habitación por categoría
    suspend fun getUniqueRooms(): List<HabitacionWrapper>

    @GET("api/rooms") // Asegúrate de que este endpoint devuelve una lista de habitaciones
    suspend fun getHabitaciones(): List<Habitacion>

    @POST("api/users/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/users/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>


    @POST("api/users/getOne")
    suspend fun getUserProfile(@Body request: ProfileRequest): Usuario

    //Calls to th

    @POST("api/bookings/getFreeRooms") // Se agregaron @Query para pasar los parámetros correctamente
    suspend fun getFreeRooms(
        @Query("startdate") startDate: String,
        @Query("enddate") endDate: String,
        @Query("numGuest") numGuest: Int
    ): List<Habitacion>

}

