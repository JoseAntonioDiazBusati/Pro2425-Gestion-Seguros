package org.example.utils

import org.example.model.IExportable
import org.example.model.Seguro
import org.example.ui.Consola
import java.io.File
import java.io.IOException

class Ficheros: IUtilFicheros {

    override fun leerArchivo(ruta: String): List<String> {
        try {
            File(ruta).readLines()
            return listOf<String>()
        }catch (e: IOException){
            Consola().mostrarError("Error al leer el fichero: ${e.message}")
            return emptyList()
        }
    }

    override fun leerSeguros(
        ruta: String,
        mapaSeguros: Map<String, (List<String>) -> Seguro>
    ): List<Seguro> {
        try {
            File(ruta).readLines()
            return listOf<Seguro>()
        } catch (e: IOException) {
            Consola().mostrarError("Error al leer el seguro: ${e.message}")
            return emptyList()
        }
    }

    override fun existeDirectorio(ruta: String): Boolean {
        return File(ruta).exists() && File(ruta).isDirectory
    }

    override fun existeFichero(ruta: String): Boolean {
        return File(ruta).exists() && File(ruta).isFile
    }

    override fun <T : IExportable> escribirArchivo(
        ruta: String,
        elementos: List<T>
    ): Boolean {
        try {
            File(ruta).writeText(elementos.joinToString("\n") { it.serializar(";") })
            return true
        } catch (e: IOException) {
            Consola().mostrarError("Error al escribir en el archivo: ${e.message}")
            return false
        }
    }

    override fun agregarLinea(ruta: String, linea: String): Boolean {
        try {
            File(ruta).appendText("$linea\n")
            return true
        } catch (e: IOException) {
            Consola().mostrarError("Error al escribir en el archivo: ${e.message}")
            return false
        }
    }
}