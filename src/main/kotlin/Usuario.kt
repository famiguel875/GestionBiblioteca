sealed class Usuario {
    data class Estudiante(val id: String, val nombre: String, val carrera: String) : Usuario()
    data class Profesor(val id: String, val nombre: String, val departamento: String) : Usuario()
    data class Visitante(val id: String, val nombre: String) : Usuario()
}