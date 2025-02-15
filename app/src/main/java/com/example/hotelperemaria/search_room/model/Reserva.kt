package com.example.hotelperemaria.search_room.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale



data class Reserva(
    @SerializedName("_id") val id: String? = null,
    @SerializedName("codigo") val codigo: String,
    @SerializedName("fechaInicio") val fechaInicio: String,
    @SerializedName("fechaFin") val fechaFin: String,
    @SerializedName("habitacion") val habitacion: String, // ObjectId en Mongo es String
    @SerializedName("usuario") val usuario: String = "67aa1964acc7f4ca632c6f78",
    @SerializedName("precio") val precio: Double,

    ) {

    companion object {
        fun stringToDate(date: String): Date? {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return try {
                dateFormat.parse(date);
            } catch (e: Exception) {
                null
            }
        }

        fun calculatePrice(fechaIni: String, fechaFin: String, precioHab: Double): Double {
            val inicio = stringToDate(fechaIni)
            val fin = stringToDate(fechaFin)

            if (inicio == null || fin == null) return 0.0 // Si alguna fecha es inválida, retorna 0

            val diffInMillis = fin.time - inicio.time
            val diffInDays = (diffInMillis / (1000 * 60 * 60 * 24)).toInt() // Convertir a días

            return if (diffInDays > 0) diffInDays * precioHab else 0.0
        }
    }
}

data class CodigoResponse(
    @SerializedName("codigo") val codigo: String
)