package org.example.app


import org.example.model.Perfil
import org.example.model.Usuario
import org.example.service.GestorUsuario
import org.example.ui.IEntradaSalida
import org.example.utils.IUtilFicheros
import java.io.File

/**
 * Clase responsable del control de acceso de usuarios: alta inicial, inicio de sesión
 * y recuperación del perfil. Su objetivo es asegurar que al menos exista un usuario
 * en el sistema antes de acceder a la aplicación.
 *
 * Esta clase encapsula toda la lógica relacionada con la autenticación de usuarios,
 * separando así la responsabilidad del acceso del resto de la lógica de negocio.
 *
 * Utiliza inyección de dependencias (DIP) para recibir los servicios necesarios:
 * - La ruta del archivo de usuarios
 * - El gestor de usuarios para registrar o validar credenciales
 * - La interfaz de entrada/salida para interactuar con el usuario
 * - La utilidad de ficheros para comprobar la existencia y contenido del fichero
 *
 * @property rutaArchivo Ruta del archivo donde se encuentran los usuarios registrados.
 * @property gestorUsuarios Servicio encargado de la gestión de usuarios (login, alta...).
 * @property ui Interfaz para mostrar mensajes y recoger entradas del usuario.
 * @property ficheros Utilidad para operar con ficheros (leer, comprobar existencia...).
 */
class ControlAcceso(
    val rutaArchivo: File,
    val gestorUsuarios: GestorUsuario,
    val ui: IEntradaSalida,
    val ficheros: IUtilFicheros
) {

    /**
     * Inicia el proceso de autenticación del sistema.
     *
     * Primero verifica si hay usuarios registrados. Si el archivo está vacío o no existe,
     * ofrece al usuario la posibilidad de crear un usuario ADMIN inicial.
     *
     * A continuación, solicita credenciales de acceso en un bucle hasta que sean válidas
     * o el usuario decida cancelar el proceso.
     *
     * @return Un par (nombreUsuario, perfil) si el acceso fue exitoso, o `null` si el usuario cancela el acceso.
     */
    fun autenticar(): Pair<String, String>? {
        if (!verificarFicheroUsuarios()) {
            return null
        }
        return iniciarSesion()
    }

    /**
     * Verifica si el archivo de usuarios existe y contiene al menos un usuario registrado.
     *
     * Si el fichero no existe o está vacío, se informa al usuario y se le pregunta si desea
     * registrar un nuevo usuario con perfil ADMIN.
     *
     * Este método se asegura de que siempre haya al menos un usuario en el sistema.
     *
     * @return `true` si el proceso puede continuar (hay al menos un usuario),
     *         `false` si el usuario cancela la creación inicial o ocurre un error.
     */
    private fun verificarFicheroUsuarios(): Boolean {
        if (!ficheros.existeFichero(rutaArchivo.path)) {
            ui.mostrarError("Archivo inexistente.")
        }
        if (gestorUsuarios.consultarTodos().isEmpty()) {
            ui.mostrar("No hay usuarios registrados.")
            val confirmar = ui.preguntar("¿Desea registrar un usuario ADMIN inicial? (s/n)")
            if (confirmar) {
                val datos = mutableListOf<String>()
                ui.mostrar("Introduce el nombre de usuario: ")
                val nomUser = readln()
                datos.add(nomUser)
                ui.mostrar("Introduce su contraseña: ")
                val contrasenia = readln()
                datos.add(contrasenia)
                val perfil = "admin"
                datos.add(perfil)
                gestorUsuarios.repoUsuarios.agregar(Usuario.crearUsuario(datos))
                return true
            } else {
                ui.mostrar("No se ha creado ningún usuario. Cancelando acceso.")
                return false
            }
        }
        return true
    }

    /**
     * Solicita al usuario sus credenciales (usuario y contraseña) en un bucle hasta
     * que sean válidas o el usuario decida cancelar.
     *
     * Si la autenticación es exitosa, se retorna el nombre del usuario y su perfil.
     *
     * @return Un par (nombreUsuario, perfil) si las credenciales son correctas,
     *         o `null` si el usuario decide no continuar.
     */
    private fun iniciarSesion(): Pair<String, String>? {
        var assist = true
        while (assist) {
            ui.mostrar("Ingrese nombre de usuario (o escriba 'salir' para cancelar):")
            val nombreUsuario = readln()

            if (nombreUsuario.lowercase() == "salir") {
                ui.mostrar("Bye bye.")
                assist = false
            }

            val usuario = gestorUsuarios.buscarUsuario(nombreUsuario)

            if (usuario == null) {
                ui.mostrar("Usuario no encontrado.")
            }

            ui.mostrar("Ingrese la clave:")
            val clave = readln()

            if (!gestorUsuarios.verificarClave(clave, usuario.clave)) {
                ui.mostrar("Clave incorrecta.")
            }

            ui.mostrar("Sesión iniciada. Bienvenido, ${usuario.nombre}.")
            return Pair(usuario.nombre, usuario.perfil.name)
        }
    }

}