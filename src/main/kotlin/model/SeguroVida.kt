package org.example.model

import java.time.LocalDate

class SeguroVida:Seguro{

    private val fechaNac: LocalDate
    private val nivelRiesgo: Riesgo
    private val indemnizacion: Double

    constructor(
        dniTitular: String,
        importe: Double,
        fechaNac: LocalDate,
        nivelRiesgo: Riesgo,
        indemnizacion: Double
    ) : super(numPoliza = ++numPolizaVida, dniTitular, importe) {
        this.fechaNac = fechaNac
        this.nivelRiesgo = nivelRiesgo
        this.indemnizacion = indemnizacion
    }

    private constructor(
        numPoliza: Int,
        dniTitular: String,
        importe: Double,
        fechaNac: LocalDate,
        nivelRiesgo: Riesgo,
        indemnizacion: Double
    ) : super(numPoliza, dniTitular, importe) {
        this.fechaNac = fechaNac
        this.nivelRiesgo = nivelRiesgo
        this.indemnizacion = indemnizacion
    }

    companion object {
        private var numPolizaVida: Int = 800000

        val INTERES_RESIDUAL = 0.005

        fun crearSeguro(datos: List<String>): SeguroVida? {
            try {
                return SeguroVida(
                    datos[0].toInt(),
                    datos[1],
                    datos[2].toDouble(),
                    LocalDate.parse(datos[3]),
                    Riesgo.getRiesgo(datos[4]),
                    datos[5].toDouble()
                )
            } catch (e: IllegalArgumentException) {
                println("Datos no válidos!!!")
            }
            return null
        }
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return (INTERES_RESIDUAL * interes)+interes
    }


    override fun serializar(separador: String): String {
        return super.serializar(separador)
    }

    override fun toString(): String {
        return super.toString()
    }
}