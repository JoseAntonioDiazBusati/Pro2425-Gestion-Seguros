package org.example.service

import org.example.data.IRepoSeguros
import org.example.model.Auto
import org.example.model.Cobertura
import org.example.model.Riesgo
import org.example.model.Seguro
import org.example.model.SeguroAuto
import org.example.model.SeguroHogar
import org.example.model.SeguroVida
import java.time.LocalDate

class GestorSeguro(
    val repoSeguros: IRepoSeguros
): IServSeguros {
    override fun contratarSeguroHogar(
        dniTitular: String,
        importe: Double,
        metrosCuadrados: Int,
        valorContenido: Double,
        direccion: String,
        anioConstruccion: Int
    ): Boolean {
        return repoSeguros.agregar((SeguroHogar(dniTitular,importe,metrosCuadrados,valorContenido,direccion,anioConstruccion)))
    }

    override fun contratarSeguroAuto(
        dniTitular: String,
        importe: Double,
        descripcion: String,
        combustible: String,
        tipoAuto: Auto,
        cobertura: Cobertura,
        asistenciaCarretera: Boolean,
        numPartes: Int
    ): Boolean {
        return repoSeguros.agregar(SeguroAuto(dniTitular,importe,descripcion,combustible,tipoAuto,cobertura,asistenciaCarretera,numPartes))
    }

    override fun contratarSeguroVida(
        dniTitular: String,
        importe: Double,
        fechaNacimiento: LocalDate,
        nivelRiesgo: Riesgo,
        indemnizacion: Double
    ): Boolean {
        return repoSeguros.agregar(SeguroVida(dniTitular,importe,fechaNacimiento,nivelRiesgo,indemnizacion))
    }

    override fun eliminarSeguro(numPoliza: Int): Boolean {
        return repoSeguros.eliminar(numPoliza)
    }

    override fun consultarTodos(): List<Seguro> {
        return repoSeguros.obtenerTodos()
    }

    override fun consultarPorTipo(tipoSeguro: String): List<Seguro> {
        return repoSeguros.obtener(tipoSeguro)
    }
}