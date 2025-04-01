package org.example.data

import org.example.model.Seguro

open class RepoSegurosMem: IRepoSeguros {

    protected val seguros = mutableListOf<Seguro>()

    override fun agregar(seguro: Seguro): Boolean {
        if (buscar(seguro.numPoliza) == null) {
            seguros.add(seguro)
            return true
        }
        return false
    }

    override fun buscar(numPoliza: Int): Seguro? {
        return seguros.find { it.numPoliza == numPoliza }
    }

    override fun eliminar(seguro: Seguro): Boolean {
        return seguros.remove(seguro)
    }

    override fun eliminar(numPoliza: Int): Boolean {
        val seguro = buscar(numPoliza)
        if (seguro != null) {
            eliminar(seguro)
        }
        return false
    }

    override fun obtenerTodos(): List<Seguro> {
        return seguros
    }

    override fun obtener(tipoSeguro: String): List<Seguro> {
        return seguros.filter { it.tipoSeguro() == tipoSeguro }
    }
}