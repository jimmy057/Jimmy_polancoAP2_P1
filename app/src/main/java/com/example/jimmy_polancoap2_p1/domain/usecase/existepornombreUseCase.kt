package com.example.jimmy_polancoap2_p1.domain.usecase

import com.example.jimmy_polancoap2_p1.domain.repository.HuacalRepository

class ExistePorCliente(
    private val repository: HuacalRepository
) {
    suspend operator fun invoke(nombre: String): Boolean {
        return repository.existePorCliente(nombre)
    }
}