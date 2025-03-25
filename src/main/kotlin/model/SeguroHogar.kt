package org.example.model


class SeguroHogar {
    /*
  constructor(
       private val metrosCuadrados: Double,
       private val valorContenido,
       private val direccion,
       private val anioConstruccion:Double,
   )
   */

    private constructor(
        numPoliza: Int,
        dniTitular: String,
        importe: Double
    ):super(numPoliza,dniTitular, importe)


    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        TODO("Not yet implemented")
    }

    override fun tipoSeguro(): String {
        TODO("Not yet implemented")
    }

    override fun serializar(separador: String): String {
        return super.serializar(separador)
    }
}