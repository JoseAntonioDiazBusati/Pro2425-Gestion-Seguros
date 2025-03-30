package org.example.model

enum class Cobertura (val descripcion: String) {
    TERCEROS ("Terceros"),
    TERCEROS_AMPLIADO ("Terceros +"),
    FRANQUICIA_200 ("Todo Riesgo con Franquicia de 200€"),
    FRANQUICIA_300 ("Todo Riesgo con Franquicia de 300€"),
    FRANQUICIA_400 ("Todo Riesgo con Franquicia de 400€"),
    FRANQUICIA_500 ("Todo Riesgo con Franquicia de 500€"),
    TODO_RIESGO ("Todo Riesgo");

    companion object{
        fun getCobertura(valor:String): Cobertura{
            return when{
                valor.lowercase() == "terceros" -> TERCEROS
                valor.lowercase() == "terceros+" -> TERCEROS_AMPLIADO
                valor.lowercase() == "franquicia200" -> FRANQUICIA_200
                valor.lowercase() == "franquicia300" -> FRANQUICIA_300
                valor.lowercase() == "franquicia400" -> FRANQUICIA_400
                valor.lowercase() == "franquicia500" -> FRANQUICIA_500
                valor.lowercase() == "todoriesgo" -> TODO_RIESGO
                else -> TERCEROS
            }
        }
    }
}