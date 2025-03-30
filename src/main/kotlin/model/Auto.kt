package org.example.model


enum class Auto {
    COCHE, MOTO, CAMION;

    companion object{
        fun getAuto(valor: String): Auto{
            return when{
                valor.lowercase() == "coche" -> COCHE
                valor.lowercase() == "moto" -> MOTO
                valor.lowercase() == "camion" -> CAMION
                else -> COCHE
            }
        }
    }
}