package org.example.model

class Usuario(
    val nombre: String,
    private val clave: String,
    val perfil: Perfil
): IExportable {

    fun cambiarClave(nuevaClaveEncriptada: String){}

    override fun serializar(separador: String): String {
        return (nombre + separador + clave + separador + perfil)
    }

    companion object{
        fun crearUsuario(datos: List<String>): Usuario{
            println("Introduce el nombre de usuario: ")
            val nombre = readln()
            println("Introduce la clave del usuario: ")
            val clave = readln()
            println("Introduce la clave del usuario: ")
            val perfil = readln()
            return Usuario(nombre,clave)
        }
    }
}