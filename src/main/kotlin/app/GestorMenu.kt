package org.example.app

import org.example.model.Perfil
import org.example.model.Usuario
import org.example.service.GestorSeguro
import org.example.service.GestorUsuario
import org.example.ui.Consola

/**
 * Clase encargada de gestionar el flujo de menús y opciones de la aplicación,
 * mostrando las acciones disponibles según el perfil del usuario autenticado.
 *
 * @property nombreUsuario Nombre del usuario que ha iniciado sesión.
 * @property perfilUsuario Perfil del usuario: admin, gestion o consulta.
 * @property ui Interfaz de usuario.
 * @property gestorUsuarios Servicio de operaciones sobre usuarios.
 * @property gestorSeguros Servicio de operaciones sobre seguros.
 */
class GestorMenu(
    val ui: Consola,
    val perfiUsuario: String,
    val nombreUsuario: Usuario,
    val gestorUsuario: GestorUsuario,
    val gestorSeguro: GestorSeguro

) {
    /**
     * Inicia un menú según el índice correspondiente al perfil actual.
     *
     * @param indice Índice del menú que se desea mostrar (0 = principal).
     */
    fun iniciarMenu(indice: Int = 0) {
        val (opciones, acciones) = ConfiguracionesApp.obtenerMenuYAcciones(perfiUsuario, indice)
        ejecutarMenu(opciones, acciones)
    }

    /**
     * Formatea el menú en forma numerada.
     */
    private fun formatearMenu(opciones: List<String>): String {
        var cadena = ""
        opciones.forEachIndexed { index, opcion ->
            cadena += "${index + 1}. $opcion\n"
        }
        return cadena
    }

    /**
     * Muestra el menú limpiando pantalla y mostrando las opciones numeradas.
     */
    private fun mostrarMenu(opciones: List<String>) {
        ui.limpiarPantalla()
        ui.mostrar(formatearMenu(opciones), salto = false)
    }

    /**
     * Ejecuta el menú interactivo.
     *
     * @param opciones Lista de opciones que se mostrarán al usuario.
     * @param ejecutar Mapa de funciones por número de opción.
     */
    private fun ejecutarMenu(opciones: List<String>, ejecutar: Map<Int, (GestorMenu) -> Boolean>) {
        do {
            mostrarMenu(opciones)
            val opcion = ui.pedirInfo("Elige opción > ").toIntOrNull()
            if (opcion != null && opcion in 1..opciones.size) {
                // Buscar en el mapa las acciones a ejecutar en la opción de menú seleccionada
                val accion = ejecutar[opcion]
                // Si la accion ejecutada del menú retorna true, debe salir del menú
                if (accion != null && accion(this)) return
            } else {
                ui.mostrarError("Opción no válida!")
            }
        } while (true)
    }

    /** Crea un nuevo usuario solicitando los datos necesarios al usuario */
    fun nuevoUsuario() {

        gestorUsuario.repoUsuarios.agregar(Usuario.crearUsuario())
    }

    /** Elimina un usuario si existe */
    fun eliminarUsuario() {
        gestorUsuario.repoUsuarios.agregar(Usuario.crearUsuario())
    }

    /** Cambia la contraseña del usuario actual */
    fun cambiarClaveUsuario() {
        gestorUsuario.repoUsuarios.cambiarClave(this.nombreUsuario)
    }

    /**
     * Mostrar la lista de usuarios (Todos o filstrados por un perfil)
     */
    fun consultarUsuarios() {
        gestorUsuario.repoUsuarios.obtenerTodos()
    }

    /**
     * Solicita al usuario un DNI y verifica que tenga el formato correcto: 8 dígitos seguidos de una letra.
     *
     * @return El DNI introducido en mayúsculas.
     */
    private fun pedirDni(): String {
        ui.mostrar("Introduce un DNI al usuario: ")
        val dni = readln()
        if (dni.last().isDigit()) {
            return dni.uppercase()
        } else {
            return "DNI no válido!!!"
        }
    }

    /**
     * Solicita al usuario un importe positivo, usado para los seguros.
     *
     * @return El valor introducido como `Double` si es válido.
     */
    private fun pedirImporte() {
        ui.pedirDouble("Introduce un importe positivo: ", "Importe no válido!!!", "No es Double!!!",)
    }

    /** Crea un nuevo seguro de hogar solicitando los datos al usuario */
    fun contratarSeguroHogar() {
        gestorSeguro.repoSeguros.agregar()
    }

    /** Crea un nuevo seguro de auto solicitando los datos al usuario */
    fun contratarSeguroAuto() {
        TODO("Implementar este método")
    }

    /** Crea un nuevo seguro de vida solicitando los datos al usuario */
    fun contratarSeguroVida() {
        TODO("Implementar este método")
    }

    /** Elimina un seguro si existe por su número de póliza */
    fun eliminarSeguro() {
        TODO("Implementar este método")
    }

    /** Muestra todos los seguros existentes */
    fun consultarSeguros() {
        gestorSeguro.repoSeguros.obtenerTodos()
    }

    /** Muestra todos los seguros de tipo hogar */
    fun consultarSegurosHogar() {
        gestorSeguro.repoSeguros.obtener(tipoSeguro = "SeguroHogar")
    }

    /** Muestra todos los seguros de tipo auto */
    fun consultarSegurosAuto() {
        gestorSeguro.repoSeguros.obtener(tipoSeguro = "SeguroAuto")
    }

    /** Muestra todos los seguros de tipo vida */
    fun consultarSegurosVida() {
        gestorSeguro.repoSeguros.obtener(tipoSeguro = "SeguroVida")
    }

}