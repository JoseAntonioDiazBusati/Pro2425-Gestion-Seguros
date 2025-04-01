package org.example.service

import org.example.data.IRepoUsuarios
import org.example.model.Perfil
import org.example.model.Usuario
import org.example.utils.IUtilSeguridad

class GestorUsuario(
    val repoUsuarios: IRepoUsuarios
) : IServUsuarios, IUtilSeguridad {

    override fun iniciarSesion(nombre: String, clave: String): Perfil? {
        val usuario = repoUsuarios.buscar(nombre)
        if (usuario != null) {
            return usuario.perfil
        }
        return null
    }

    override fun agregarUsuario(nombre: String, clave: String, perfil: Perfil): Boolean {
        return repoUsuarios.agregar(Usuario(nombre, clave, perfil))
    }

    override fun eliminarUsuario(nombre: String): Boolean {
        if (repoUsuarios.buscar(nombre) != null) {
            repoUsuarios.eliminar(nombre)
            return true
        }
        return false
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        return repoUsuarios.cambiarClave(usuario, nuevaClave)
    }

    override fun buscarUsuario(nombre: String): Usuario? {
        return repoUsuarios.buscar(nombre)
    }

    override fun consultarTodos(): List<Usuario> {
        return repoUsuarios.obtenerTodos()
    }

    override fun consultarPorPerfil(perfil: Perfil): List<Usuario> {
        return repoUsuarios.obtener(perfil)
    }

    override fun encriptarClave(clave: String, nivelSeguridad: Int): String {
        TODO("Not yet implemented")
    }

    override fun verificarClave(claveIngresada: String, hashAlmacenado: String): Boolean {
        TODO("Not yet implemented")
    }
}