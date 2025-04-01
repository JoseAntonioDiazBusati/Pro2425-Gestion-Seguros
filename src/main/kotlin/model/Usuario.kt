package org.example.model

import org.example.ui.Consola

class Usuario(
    val nombre: String,
    clave: String,
    val perfil: Perfil
): IExportable {

    var clave: String = clave
        private set

    fun cambiarClave(nuevaClaveEncriptada: String){
        Consola().mostrar("Introduzca la antigua clave para cambiarla: ")
        val antiguaClave = readln()
        if (antiguaClave.lowercase() == clave.lowercase()){
            clave = nuevaClaveEncriptada
            Consola().mostrar("Clave actualizada")
        }else{
            Consola().mostrar("No coinciden. Accion denegada")
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