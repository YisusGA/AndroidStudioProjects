package es.dam2.actividadrutascardviewparcelable

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dato (
    val image: Int,
    val titulo: String,
    val distancia: String,
    val dificultad: String,
    val duracion: String,
    val descripcion: String,
    var esFavorita: Boolean
) : Parcelable
