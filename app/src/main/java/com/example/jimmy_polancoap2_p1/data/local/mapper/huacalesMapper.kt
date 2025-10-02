package com.example.jimmy_polancoap2_p1.data.local.mapper

import com.example.jimmy_polancoap2_p1.data.local.entities.HuacalEntity
import com.example.jimmy_polancoap2_p1.domain.model.Huacal

fun HuacalEntity.toDomain(): Huacal =
    Huacal(
        id = this.idEntrada,
        fecha = this.fecha,
        cliente = this.nombreCliente,
        cantidad = this.cantidad,
        precio = this.precio
    )
fun Huacal.toEntity(): HuacalEntity =
    HuacalEntity(
        idEntrada = this.id,
        fecha = this.fecha,
        nombreCliente = this.cliente,
        cantidad = this.cantidad,
        precio = this.precio
    )