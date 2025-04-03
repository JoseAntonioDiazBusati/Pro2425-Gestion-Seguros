package org.example.ui

import java.io.File
import org.jline.reader.EndOfFileException
import org.jline.reader.LineReaderBuilder
import org.jline.reader.UserInterruptException
import org.jline.terminal.TerminalBuilder


class Consola : IEntradaSalida {

    override fun mostrar(msj: String, salto: Boolean, pausa: Boolean) {
        println(msj)
    }

    override fun mostrarError(msj: String, pausa: Boolean) {
        println("ERROR -" + msj)
    }

    override fun pedirInfo(msj: String): String {
        if (msj.isNotBlank()) {
            mostrar(msj, false)
        }
        return readln().trim()
    }

    override fun pedirInfo(
        msj: String,
        error: String,
        debeCumplir: (String) -> Boolean
    ): String {
        val entrada = pedirInfo(msj)
        require(debeCumplir(entrada)){ mostrarError(error) }
        return entrada.trim()
    }

    override fun pedirDouble(
        prompt: String,
        error: String,
        errorConv: String,
        debeCumplir: (Double) -> Boolean
    ): Double {
        mostrar("Introduce un numero decimal: ")
        val entrada = readln().replace (",",".")
        val numDouble = entrada.toDoubleOrNull()
        require(numDouble != null){ mostrarError(errorConv) }
        require(debeCumplir(numDouble)){ mostrarError(error) }
        return numDouble
    }

    override fun pedirEntero(
        prompt: String,
        error: String,
        errorConv: String,
        debeCumplir: (Int) -> Boolean
    ): Int {
        val entrada = readln()
        val numInt = entrada.toIntOrNull()
        require(numInt != null){ mostrarError(errorConv) }
        require(debeCumplir(numInt)){ mostrarError(error) }
        return numInt
    }

    override fun pedirInfoOculta(prompt: String): String {
        return try {
            val terminal = TerminalBuilder.builder()
                .dumb(true) // Para entornos no interactivos como IDEs
                .build()

            val reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build()

            reader.readLine(prompt, '*') // Oculta la contraseña con '*'
        } catch (e: UserInterruptException) {
            mostrarError("Entrada cancelada por el usuario (Ctrl + C).", pausa = false)
            ""
        } catch (e: EndOfFileException) {
            mostrarError("Se alcanzó el final del archivo (EOF ó Ctrl+D).", pausa = false)
            ""
        } catch (e: Exception) {
            mostrarError("Problema al leer la contraseña: ${e.message}", pausa = false)
            ""
        }
    }

    override fun pausar(msj: String) {
        pedirInfo(msj)
    }

    override fun limpiarPantalla(numSaltos: Int) {
        if (System.console() != null) {
            mostrar("\u001b[H\u001b[2J", false)
            System.out.flush()
        } else {
            repeat(numSaltos) {
                mostrar("")
            }
        }
    }

    override fun preguntar(mensaje: String): Boolean {
        do {
            mostrar(mensaje)
            val valor = readln()
            if (valor.lowercase() == "s") {
                mostrar("Confirmado!")
                return true
            }
            if (valor.lowercase() == "n") {
                mostrar("No se ha confirmado!")
                return false
            }
        } while (valor.lowercase() != "s" && valor.lowercase() != "n")
        return false
    }
}
