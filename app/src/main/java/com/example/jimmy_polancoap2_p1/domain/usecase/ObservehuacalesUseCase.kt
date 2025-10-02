package com.example.jimmy_polancoap2_p1.domain.usecase

import com.example.jimmy_polancoap2_p1.domain.model.Huacal
import com.example.jimmy_polancoap2_p1.domain.repository.HuacalRepository
import kotlinx.coroutines.flow.Flow

class ObserveHuacales(
    private val repository: HuacalRepository
) {
    operator fun invoke(): Flow<List<Huacal>> {
        return repository.observeEntradas()
    }
}