package com.example.jimmy_polancoap2_p1.domain.usecase

import com.example.jimmy_polancoap2_p1.domain.model.Huacal
import com.example.jimmy_polancoap2_p1.domain.repository.HuacalRepository

class GetHuacal(
    private val repository: HuacalRepository
) {
    suspend operator fun invoke(id: Int): Huacal? {
        return repository.getEntrada(id)
    }
}