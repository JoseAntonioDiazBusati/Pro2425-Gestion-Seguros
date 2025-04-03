package org.example

import org.example.app.CargadorInicial
import org.example.data.*
import org.example.ui.Consola
import org.example.utils.Ficheros
import org.example.utils.Seguridad


fun main() {
    // Crear dos variables con las rutas de los archivos de texto donde se almacenan los usuarios y seguros.
    // Estos ficheros se usarán solo si el programa se ejecuta en modo de almacenamiento persistente.
    val ficherosUsuarios = "/src/main/kotlin/RegistroUsuario.txt"
    val ficherosSeguros = "/src/main/kotlin/RegistroSeguros.txt"

    // Instanciamos los componentes base del sistema: la interfaz de usuario, el gestor de ficheros y el módulo de seguridad.
    // Estos objetos serán inyectados en los diferentes servicios y utilidades a lo largo del programa.
    val ui = Consola()
    val ficheros = Ficheros()
    val seguridad = Seguridad()

    // Limpiamos la pantalla antes de comenzar, para que la interfaz esté despejada al usuario.
    ui.limpiarPantalla()

    // Preguntamos al usuario si desea iniciar en modo simulación.
    // En modo simulación los datos no se guardarán en archivos, solo estarán en memoria durante la ejecución.
    val modo = ui.preguntar("¿Desea entrar en el modo simulacion? (s/n):")

    // Declaramos los repositorios de usuarios y seguros.
    // Se asignarán más abajo dependiendo del modo elegido por el usuario.

    // Si se ha elegido modo simulación, se usan repositorios en memoria.
    // Si se ha elegido almacenamiento persistente, se instancian los repositorios que usan ficheros.
    // Además, creamos una instancia del cargador inicial de información y lanzamos la carga desde los ficheros.
    if (modo) {
        val repoUsuario = RepoUsuariosMem()
        val repoSeguro = RepoSegurosMem()
    } else {
        val repoUsuario = RepoUsuariosFich(ficherosUsuarios, ficheros)
        val repoSeguro = RepoSegurosFich(ficherosSeguros, ficheros)
        CargadorInicial(ui, repoSeguro, repoUsuario).cargarSeguros()
        CargadorInicial(ui, repoSeguro, repoUsuario).cargarUsuarios()
    }

    // Se crean los servicios de lógica de negocio, inyectando los repositorios y el componente de seguridad.


    // Se inicia el proceso de autenticación. Se comprueba si hay usuarios en el sistema y se pide login.
    // Si no hay usuarios, se permite crear un usuario ADMIN inicial.


    // Si el login fue exitoso (no es null), se inicia el menú correspondiente al perfil del usuario autenticado.
    // Se lanza el menú principal, **iniciarMenu(0)**, pasándole toda la información necesaria.
}