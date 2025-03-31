package org.example.ui

class Consola: IEntradaSalida {
    override fun mostrar(msj: String, salto: Boolean, pausa: Boolean) {
        println(msj)
    }

    override fun mostrarError(msj: String, pausa: Boolean) {
        println(msj)
    }

    override fun pedirInfo(msj: String): String {
        TODO("Not yet implemented")
    }

    override fun pedirInfo(
        msj: String,
        error: String,
        debeCumplir: (String) -> Boolean
    ): String {
        TODO("Not yet implemented")
    }

    override fun pedirDouble(
        prompt: String,
        error: String,
        errorConv: String,
        debeCumplir: (Double) -> Boolean
    ): Double {
        TODO("Not yet implemented")
    }

    override fun pedirEntero(
        prompt: String,
        error: String,
        errorConv: String,
        debeCumplir: (Int) -> Boolean
    ): Int {
        TODO("Not yet implemented")
    }

    override fun pedirInfoOculta(prompt: String): String {
        TODO("Not yet implemented")
    }

    override fun pausar(msj: String) {
        TODO("Not yet implemented")
    }

    override fun limpiarPantalla(numSaltos: Int) {
        TODO("Not yet implemented")
    }

    override fun preguntar(mensaje: String): Boolean {
        TODO("Not yet implemented")
    }
}
