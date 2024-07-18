package com.ramsoft.dogsearchapp.di

import android.content.Context
import androidx.room.Room
import com.ramsoft.dogsearchapp.database.AppDatabase

import com.ramsoft.dogsearchapp.database.FavoriteDogDAO
import com.ramsoft.dogsearchapp.database.ViewLaterDogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideDogImageDao(database: AppDatabase): FavoriteDogDAO {
        return database.favouriteDogDAO()
    }

    @Provides
    @Singleton
    fun provideViewLaterDao(database: AppDatabase): ViewLaterDogDao {
        return database.viewLaterDogDao()
    }

}