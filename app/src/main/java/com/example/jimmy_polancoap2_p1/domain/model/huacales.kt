package com.example.jimmy_polancoap2_p1.domain.model

data class Huacal(
    val id: Int = 0,
    val fecha: String,
    val cliente: String,
    val cantidad: Int,
    val precio: Double
)
