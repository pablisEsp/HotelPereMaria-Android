package com.example.hotelperemaria.rooms.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.hotelperemaria.R
import com.google.gson.annotations.SerializedName

data class HabitacionWrapper(
    @SerializedName("habitacion") val habitacion: Habitacion
)

data class Habitacion(
    @SerializedName("codigo") val codigo: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("categoria") val categoria: String,
    @SerializedName("camas") val camas: List<Cama>,
    @SerializedName("tamanyo") val tamanyo: Double,
    @SerializedName("servicios") val serviciosString: List<String>,  // Recibimos los nombres como `List<String>`
    @SerializedName("numPersonas") val numPersonas: Int,
    @SerializedName("precio") val precio: Double,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("imagenes") val imagenes: List<String>,
    @SerializedName("habilitada") val habilitada: Boolean
)

data class Cama(
    @SerializedName("numCamas") val numCamas: Int,
    @SerializedName("nombre") val nombre: String
)

data class Servicio(
    val nombre: String,  // Nombre del servicio
    val icono: String    // Almacena el nombre del recurso drawable
)

/**
 * Función para convertir `serviciosString` en una lista de `Servicio`
 */

fun mapServicios(serviciosString: List<String>): List<Servicio> {
    val servicioMap = mapOf(
        "WiFi" to "wifi",
        "AC" to "ac",
        "TV" to "television",
        "Bar" to "bar",
        "Piscina" to "swimmingpool",
        "GYM" to "gym",
        "Spa" to "spa_black",
        "Servicio Premium de Habitaciones" to "premiumroomservice",
        "Oficina" to "office",
        "Cocina" to "kitchen",
        "Nevera" to "fridge",
        "Cafetera" to "coffemaker",
        "Mesa de Billar" to "billiard",
        "Balcón" to "balcony",
        "Plancha" to "iron",
        "Minibar" to "minibar"
    )

    return serviciosString.mapNotNull { nombre ->
        servicioMap[nombre]?.let { iconoResId ->
            Servicio(nombre, iconoResId)
        }
    }
}

/**
 * Función para obtener `Painter` en la UI
 */
@Composable
fun getPainterFromName(iconoName: String): Painter {
    val resourceId = when (iconoName) {
        "wifi" -> R.drawable.wifi
        "ac" -> R.drawable.ac
        "television" -> R.drawable.television
        "bar" -> R.drawable.bar
        "swimmingpool" -> R.drawable.swimmingpool
        "gym" -> R.drawable.gym
        "spa_black" -> R.drawable.spa_black
        "premiumroomservice" -> R.drawable.premiumroomservice
        "office" -> R.drawable.office
        "kitchen" -> R.drawable.kitchen
        "fridge" -> R.drawable.fridge
        "coffemaker" -> R.drawable.coffemaker
        "billiard" -> R.drawable.billiard
        "balcony" -> R.drawable.balcony
        "iron" -> R.drawable.iron
        "minibar" -> R.drawable.minibar

        //default icon
        else -> R.drawable.ic_default
    }

    return painterResource(id = resourceId)
}
