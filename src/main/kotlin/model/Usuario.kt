package org.example.model

class Usuario(
    val nombre: String,
    private var clave: String,
    val perfil: Perfil
): IExportable {

    fun cambiarClave(nuevaClaveEncriptada: String){
        println("Introduzca la antigua clave para cambiarla: ")
        val antiguaClave = readln()
        if (antiguaClave.lowercase() == clave.lowercase()){
            clave = nuevaClaveEncriptada
            println("Clave actualizada")
        }else{
            println("No coinciden. Accion denegada")
        }
    }

    override fun serializar(separador: String): String {
        return (nombre + separador + clave + separador + perfil)
    }

    companion object{
        fun crearUsuario(datos: List<String>): Usuario{
            return Usuario(datos[0],datos[1],Perfil.getPerfil(datos[2]))
        }
    }
}