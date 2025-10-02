package com.example.jimmy_polancoap2_p1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jimmy_polancoap2_p1.data.local.dao.HuacalDao
import com.example.jimmy_polancoap2_p1.data.local.entities.HuacalEntity

@Database(
    entities = [
        HuacalEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun HuacalDao(): HuacalDao
}