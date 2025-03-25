package org.example.model

enum class Perfil () {
    ADMIN, // Puede gestionar seguros y usuarios
    GESTION, // Puede gestionar seguros pero no puede crearlos o eliminarlos
    CONSULTA; // Solo puede ver informacion

    companion object{
        fun getPerfil(valor: String):Perfil{
            return ADMIN
        }
    }
}