package com.example.hotelperemaria.login.model

import com.google.gson.annotations.SerializedName

data class UsuarioWrapper(
    @SerializedName("Usuario") val usuario: Usuario
)

data class Usuario(
    @SerializedName("_id") val id: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellido1") val apellido1: String,
    @SerializedName("apellido2") val apellido2: String?,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("dniPasaporte") val dniPasaporte: String,
    @SerializedName("movil") val movil: String,
    @SerializedName("fechaNacimiento") val fechaNacimiento: String, // Formato de fecha como String (ISO 8601)
    @SerializedName("fechaRegistro") val fechaRegistro: String?, // Puede ser nulo si usa el valor por defecto
    @SerializedName("tipo") val tipo: String, // Puede ser 'cliente', 'admin' o 'empleado'
    @SerializedName("reservas") val reservas: List<String>, // Lista de IDs de reservas
    @SerializedName("avatar") val avatar: String?
)