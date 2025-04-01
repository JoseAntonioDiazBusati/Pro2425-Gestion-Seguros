package org.example.data

import org.example.model.Perfil
import org.example.model.Usuario
import org.example.utils.IUtilSeguridad

open class RepoUsuariosMem : IRepoUsuarios, IUtilSeguridad {

    protected val usuarios = mutableListOf<Usuario>()

    override fun encriptarClave(clave: String, nivelSeguridad: Int): String {
        TODO("Not yet implemented")
    }

    override fun verificarClave(claveIngresada: String, hashAlmacenado: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun agregar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) == null) {
            usuarios.add(usuario)
            return true
        }
        return false
    }

    override fun buscar(nombreUsuario: String): Usuario? {
        return usuarios.find { it.nombre == nombreUsuario }
    }

    override fun eliminar(usuario: Usuario): Boolean {
        return usuarios.remove(usuario)
    }

    override fun eliminar(nombreUsuario: String): Boolean {
        val usuario = buscar(nombreUsuario)
        if (usuario != null) {
            eliminar(usuario)
        }
        return false
    }

    override fun obtenerTodos(): List<Usuario> {
        return usuarios
    }

    override fun obtener(perfil: Perfil): List<Usuario> {
        return usuarios.filter { it.perfil == perfil }
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        usuario.cambiarClave(nuevaClave)
        return true
    }
}