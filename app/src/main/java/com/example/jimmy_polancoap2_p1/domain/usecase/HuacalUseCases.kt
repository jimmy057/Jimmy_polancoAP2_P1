package com.example.jimmy_polancoap2_p1.domain.usecase

import javax.inject.Inject
import com.example.jimmy_polancoap2_p1.domain.usecase.GetHuacal
import com.example.jimmy_polancoap2_p1.domain.usecase.UpsertHuacal
import com.example.jimmy_polancoap2_p1.domain.usecase.DeleteHuacal
import com.example.jimmy_polancoap2_p1.domain.usecase.ObserveHuacales
import com.example.jimmy_polancoap2_p1.domain.usecase.ExistePorCliente

data class HuacalUseCases @Inject constructor(
    val observeHuacales: ObserveHuacales,
    val getHuacal: GetHuacal,
    val upsertHuacal: UpsertHuacal,
    val deleteHuacal: DeleteHuacal,
    val existePorCliente: ExistePorCliente
)