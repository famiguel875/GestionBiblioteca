/** Constantes que indican el tiempo que los profesores y alumnos pueden tomar un prestamo */
const val DURACION_MAXIMA_ESTUDIANTE = 7 // Duración máxima de préstamo para estudiantes en días
const val DURACION_MAXIMA_PROFESOR = 14  // Duración máxima de préstamo para profesores en días

/** Función para verificar si un usuario puede tomar un prestamo */
fun puedeTomarPrestado(usuario: Usuario, material: Any): String {
    return when (usuario) {
        is Usuario.Visitante -> "Los visitantes no pueden tomar prestados materiales."
        is Usuario.Estudiante -> {
            if (material is Libro) {
                "El estudiante ${usuario.nombre} puede tomar prestado el libro \"${material.titulo}\" por un máximo de $DURACION_MAXIMA_ESTUDIANTE días."
            } else {
                "Los estudiantes solo pueden tomar prestados libros."
            }
        }
        is Usuario.Profesor -> {
            val duracionMaxima = when (material) {
                is Libro -> DURACION_MAXIMA_PROFESOR
                is Revista -> DURACION_MAXIMA_PROFESOR
                is DVD -> DURACION_MAXIMA_PROFESOR
                else -> 0 // Si el tipo de material no está reconocido, se establece una duración máxima de 0 días
            }
            val tipoMaterial = when (material) {
                is Libro -> "libro"
                is Revista -> "revista"
                is DVD -> "DVD"
                else -> "material no reconocido"
            }
            val titulo = when (material) {
                is Libro -> material.titulo
                is Revista -> material.titulo
                is DVD -> material.titulo
                else -> ""
            }
            if (duracionMaxima > 0) {
                "El profesor ${usuario.nombre} puede tomar prestado el $tipoMaterial \"$titulo\" por un máximo de $duracionMaxima días."
            } else {
                "El profesor ${usuario.nombre} no puede tomar prestado este tipo de $tipoMaterial."
            }
        }
    }
}

fun main() {
    val estudiante = Usuario.Estudiante("123", "Juan", "Ingeniería")
    val libro = Libro("El principito", "Antoine de Saint-Exupéry", 1943)
    val dvd = DVD("Pulp Fiction", "Quentin Tarantino", 1994)

    println(puedeTomarPrestado(estudiante, libro))

    val visitante = Usuario.Visitante("456", "María")
    println(puedeTomarPrestado(visitante, libro))

    val profesor = Usuario.Profesor("789", "Dr. Smith", "Matemáticas")

    println(puedeTomarPrestado(profesor, libro))
    println(puedeTomarPrestado(profesor, dvd))
}