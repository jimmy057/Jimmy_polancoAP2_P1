package com.example.jimmy_polancoap2_p1.data.local.mapper

import com.example.jimmy_polancoap2_p1.data.local.entities.HuacalEntity

fun HuacalEntity.toDomain(): HuacalEntity =
    HuacalEntity(
        idEntrada = idEntrada,
        fecha = fecha,
        nombreCliente = nombreCliente,
        cantidad = cantidad,
        precio = precio
    )

fun HuacalEntity.toEntity(): HuacalEntity =
    HuacalEntity(
        idEntrada = idEntrada,
        fecha = fecha,
        nombreCliente = nombreCliente,
        cantidad = cantidad,
        precio = precio
    )
