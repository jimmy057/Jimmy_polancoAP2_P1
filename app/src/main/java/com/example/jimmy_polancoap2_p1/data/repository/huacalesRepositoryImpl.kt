package com.example.jimmy_polancoap2_p1.data.repository

import com.example.jimmy_polancoap2_p1.data.local.dao.HuacalDao
import com.example.jimmy_polancoap2_p1.data.local.mapper.toDomain
import com.example.jimmy_polancoap2_p1.data.local.mapper.toEntity
import com.example.jimmy_polancoap2_p1.domain.model.Huacal
import com.example.jimmy_polancoap2_p1.domain.repository.HuacalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HuacalRepositoryImpl(
    private val dao: HuacalDao
) : HuacalRepository {

    override fun observeEntradas(): Flow<List<Huacal>> =
        dao.observeEntradas().map { list -> list.map { it.toDomain() } }

    override suspend fun getEntrada(id: Int): Huacal? =
        dao.getById(id)?.toDomain()

    override suspend fun upsert(entrada: Huacal): Int =
        dao.upsert(entrada.toEntity()).toInt()

    override suspend fun delete(id: Int) {
        dao.getById(id)?.let { dao.eliminar(it) }
    }

    override suspend fun buscarPorCliente(nombre: String): List<Huacal> =
        dao.buscarPorCliente(nombre).map { it.toDomain() }

    override suspend fun existePorCliente(nombre: String): Boolean =
        dao.existsByCliente(nombre)
}


