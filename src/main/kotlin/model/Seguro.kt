package org.example.model


abstract class Seguro(
    var numPoliza: Int,
    private val dniTitular: String,
    protected val importe: Double
): IExportable {

    abstract fun calcularImporteAnioSiguiente(interes: Double): Double

    fun tipoSeguro():String{
        val seguro = this::class.simpleName
        if (seguro == null) {
            return "Desconocido"
        } else {
            return seguro
        }
    }

    override fun serializar(separador: String): String {
        return "${numPoliza}$separador$dniTitular$separador${"%2.f".format(importe)}"
    }

    override fun toString(): String {
        return "Seguro(numPoliza=$numPoliza, dniTitular=$dniTitular, importe=${"%2.f".format(importe)})"
    }

    override fun hashCode(): Int {
        return numPoliza.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other is Seguro && this.numPoliza == other.numPoliza
    }
}