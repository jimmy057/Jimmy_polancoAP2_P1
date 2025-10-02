package com.example.jimmy_polancoap2_p1.domain.repository

import com.example.jimmy_polancoap2_p1.domain.model.Huacal
import kotlinx.coroutines.flow.Flow

interface HuacalRepository {
    fun observeEntradas(): Flow<List<Huacal>>

    suspend fun getEntrada(id: Int): Huacal?

    suspend fun upsert(entrada: Huacal): Int

    suspend fun delete(id: Int)

    suspend fun buscarPorCliente(nombre: String): List<Huacal>

    suspend fun existePorCliente(nombre: String): Boolean
}
