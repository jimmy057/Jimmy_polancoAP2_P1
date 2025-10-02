package com.example.jimmy_polancoap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.jimmy_polancoap2_p1.data.local.entities.HuacalEntity

@Dao
interface HuacalDao {
    @Insert
    suspend fun insertar(entrada: HuacalEntity)

    @Update
    suspend fun actualizar(entrada: HuacalEntity)

    @Delete
    suspend fun eliminar(entrada: HuacalEntity)

    @Query("SELECT * FROM EntradasHuacales WHERE nombreCliente LIKE '%' || :cliente || '%'")
    suspend fun buscarPorCliente(cliente: String): List<HuacalEntity>

    @Query("SELECT * FROM EntradasHuacales")
    suspend fun listar(): List<HuacalEntity>
}