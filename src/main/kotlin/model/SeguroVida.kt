package org.example.model

class SeguroVida(
    numPoliza: Int,
    dniTitular: String,
    importe: Double
):Seguro(numPoliza, dniTitular, importe) {

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