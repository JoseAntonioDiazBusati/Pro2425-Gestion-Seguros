package org.example.app

import org.example.data.ICargarSegurosIniciales
import org.example.data.ICargarUsuariosIniciales
import org.example.ui.IEntradaSalida

/**
 * Clase encargada de cargar los datos iniciales de usuarios y seguros desde ficheros,
 * necesarios para el funcionamiento del sistema en modo persistente.
 *
 * @param ui Interfaz de entrada/salida para mostrar mensajes de error.
 * @param repoUsuarios Repositorio que permite cargar usuarios desde un fichero.
 * @param repoSeguros Repositorio que permite cargar seguros desde un fichero.
 */
class CargadorInicial(
    val ui: IEntradaSalida,
    val repoSeguro: ICargarSegurosIniciales,
    val repoUsuario: ICargarUsuariosIniciales
) {

    /**
     * Carga los usuarios desde el archivo configurado en el repositorio.
     * Muestra errores si ocurre un problema en la lectura o conversión de datos.
     */
    fun cargarUsuarios() {
        try {
            val usuarios = repoUsuario.cargarUsuarios()
            ui.mostrar("Usuarios cargados.")
        } catch (e: Exception) {
            ui.mostrarError("No se han podido cargar los usuarios.")
        }
    }

    /**
     * Carga los seguros desde el archivo configurado en el repositorio.
     * Utiliza el mapa de funciones de creación definido en la configuración de la aplicación
     * (ConfiguracionesApp.mapaCrearSeguros).
     * Muestra errores si ocurre un problema en la lectura o conversión de datos.
     */
    fun cargarSeguros() {
        try {
            val seguros = repoSeguro.cargarSeguros()
            ui.mostrar("Seguros cargados.")
        } catch (e: Exception) {
            ui.mostrarError("No se han podido cargar los seguros.")
        }
    }

}