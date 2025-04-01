package org.example.data

import org.example.model.Usuario
import org.example.utils.IUtilFicheros

class RepoUsuariosFich(
    private val rutaArchivo: String,
    private val fich: IUtilFicheros
): RepoUsuariosMem(), ICargarUsuariosIniciales {
    override fun agregar(usuario: Usuario): Boolean {
        if ((fich.agregarLinea(rutaArchivo, usuario.serializar()))){
            return true
        }
        return false
    }

    override fun eliminar(usuario: Usuario): Boolean {

        return super.eliminar(usuario)
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        usuario.cambiarClave(nuevaClave)
        return fich.escribirArchivo(rutaArchivo,usuarios)
    }

    override fun cargarUsuarios(): Boolean {
        val lineas = fich.leerArchivo(rutaArchivo)

        if (lineas.isNotEmpty()){
            usuarios.clear()
            for (linea in lineas){
                val datos = linea.split(";")
                if (datos.size == 3){
                    usuarios.add(Usuario.crearUsuario(datos))
                }
            }
            return usuarios.isNotEmpty()
        }
        return false
    }
}