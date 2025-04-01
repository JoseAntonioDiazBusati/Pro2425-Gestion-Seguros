package org.example.model


class SeguroAuto : Seguro {

    private val descripcion: String
    private var combustible: String
    private val tipoAuto: Auto
    private val cobertura: Cobertura
    private val asistenciaCarretera: Boolean
    private val numPartes: Int

    constructor(
        dniTitular: String,
        importe: Double,
        descripcion: String,
        combustible: String,
        tipoAuto: Auto,
        cobertura: Cobertura,
        asistenciaCarretera: Boolean,
        numPartes: Int
    ) : super(numPoliza = ++numPolizasAuto, dniTitular, importe) {
        this.descripcion = descripcion
        this.combustible = combustible
        this.tipoAuto = tipoAuto
        this.cobertura = cobertura
        this.asistenciaCarretera = asistenciaCarretera
        this.numPartes = numPartes
    }

    private constructor(
        numPoliza: Int,
        dniTitular: String,
        importe: Double,
        descripcion: String,
        combustible: String,
        tipoAuto: Auto,
        cobertura: Cobertura,
        asistenciaCarretera: Boolean,
        numPartes: Int
    ) : super(numPoliza, dniTitular, importe) {
        this.descripcion = descripcion
        this.combustible = combustible
        this.tipoAuto = tipoAuto
        this.cobertura = cobertura
        this.asistenciaCarretera = asistenciaCarretera
        this.numPartes = numPartes
    }

    companion object {
        private var numPolizasAuto: Int = 400000

        val PORCENTAJE_INCREMENTO_PARTES = 2

        fun crearSeguro(datos: List<String>): SeguroAuto {
            return SeguroAuto(
                datos[0].toInt(),
                datos[1],
                datos[2].toDouble(),
                datos[3],
                datos[4],
                Auto.getAuto(datos[5]),
                Cobertura.getCobertura(datos[6]),
                datos[7].toBoolean(),
                datos[8].toInt()
            )
        }
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return ((interes * PORCENTAJE_INCREMENTO_PARTES)/100) + interes
    }

    override fun serializar(separador: String): String {
        return super.serializar(separador)
    }

    override fun toString(): String {
        return super.toString()
    }
}