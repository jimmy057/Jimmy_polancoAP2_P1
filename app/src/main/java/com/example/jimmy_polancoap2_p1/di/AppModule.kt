package com.example.jimmy_polancoap2_p1.di

import android.content.Context
import androidx.room.Room
import com.example.jimmy_polancoap2_p1.data.local.dao.HuacalDao
import com.example.jimmy_polancoap2_p1.data.local.database.AppDatabase
import com.example.jimmy_polancoap2_p1.data.repository.HuacalRepositoryImpl
import com.example.jimmy_polancoap2_p1.domain.repository.HuacalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "huacal_db"
        ).build()
    }

    @Provides
    fun provideHuacalDao(db: AppDatabase): HuacalDao = db.HuacalDao()

    @Provides
    @Singleton
    fun provideHuacalRepository(dao: HuacalDao): HuacalRepository {
        return HuacalRepositoryImpl(dao)
    }
}