package org.example.data

import org.example.model.Seguro
import org.example.model.SeguroHogar
import org.example.model.SeguroAuto
import org.example.model.SeguroVida
import org.example.utils.IUtilFicheros

class RepoSegurosFich(
    private val rutaArchivo: String,
    private val fich: IUtilFicheros
): RepoSegurosMem(),ICargarSegurosIniciales {

    override fun agregar(seguro: Seguro): Boolean {
        if ((fich.agregarLinea(rutaArchivo, seguro.serializar()))) {
            return true
        }
        return false
    }

    override fun eliminar(seguro: Seguro): Boolean {
        if (fich.escribirArchivo(rutaArchivo, seguros.filter { it != seguro })) {
            return super.eliminar(seguro)
        }
        return false
    }

    override fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro>): Boolean {
        TODO("Not yet implemented")
    }
    private fun actualizarContadores(seguros: List<Seguro>) {
        // Actualizar los contadores de polizas del companion object según el tipo de seguro
        val maxHogar = seguros.filter { it.tipoSeguro() == "SeguroHogar" }.maxOfOrNull { it.numPoliza }
        val maxAuto = seguros.filter { it.tipoSeguro() == "SeguroAuto" }.maxOfOrNull { it.numPoliza }
        val maxVida = seguros.filter { it.tipoSeguro() == "SeguroVida" }.maxOfOrNull { it.numPoliza }

        if (maxHogar != null) SeguroHogar.numPolizaHogar = maxHogar
        if (maxAuto != null) SeguroAuto.numPolizasAuto = maxAuto
        if (maxVida != null) SeguroVida.numPolizaVida = maxVida
    }
}