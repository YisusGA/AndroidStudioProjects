package es.dam2.reproductormusica

data class Cancion(
    val titulo: String,
    val autor: String,
    val pista: Int // El tercer parámetro, que le dice la Canción, se almacena como un Int (entero)
)
