package com.ramsoft.dogsearchapp.di


import com.ramsoft.dogsearchapp.database.FavoriteDogDAO
import com.ramsoft.dogsearchapp.database.ViewLaterDogDao
import com.ramsoft.dogsearchapp.networking.ApiServices
import com.ramsoft.dogsearchapp.repository.DogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton


@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun providerRandomRepo(apiService: ApiServices, favoriteDogDao: FavoriteDogDAO, viewLaterDogDao: ViewLaterDogDao) : DogRepository {
        return DogRepository(apiService, favoriteDogDao ,viewLaterDogDao )
    }

    @Provides
    @Singleton
    fun providerSearchByRepo(apiService: ApiServices, favoriteDogDao: FavoriteDogDAO, viewLaterDogDao: ViewLaterDogDao) : DogRepository {
        return DogRepository(apiService , favoriteDogDao , viewLaterDogDao)
    }
}