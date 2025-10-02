package com.example.jimmy_polancoap2_p1.domain.usecase

import com.example.jimmy_polancoap2_p1.domain.repository.HuacalRepository
import javax.inject.Inject

class DeleteHuacal @Inject constructor(
    private val repository: HuacalRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.delete(id)
    }
}