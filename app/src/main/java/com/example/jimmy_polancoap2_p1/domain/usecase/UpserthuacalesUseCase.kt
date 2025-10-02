package com.example.jimmy_polancoap2_p1.domain.usecase

import com.example.jimmy_polancoap2_p1.domain.model.Huacal
import com.example.jimmy_polancoap2_p1.domain.repository.HuacalRepository
import javax.inject.Inject

class UpsertHuacal @Inject constructor(
    private val repository: HuacalRepository
) {
    suspend operator fun invoke(huacal: Huacal): Int {
        return repository.upsert(huacal)
    }
}