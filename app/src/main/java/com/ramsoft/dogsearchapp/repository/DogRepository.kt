package com.ramsoft.dogsearchapp.repository


import com.ramsoft.dogsearchapp.database.FavoriteDog
import com.ramsoft.dogsearchapp.database.FavoriteDogDAO
import com.ramsoft.dogsearchapp.database.ViewLaterDog
import com.ramsoft.dogsearchapp.database.ViewLaterDogDao

import com.ramsoft.dogsearchapp.networking.ApiServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogRepository @Inject constructor(private  val apiServices: ApiServices, private val favoriteDogDao: FavoriteDogDAO,
                                        private val viewLaterDogDao: ViewLaterDogDao
)
{
    suspend fun getRandomDog(): String{
        val response = apiServices.getRandomDogImage()
        return if (response.isSuccessful && response.body()?.status == "success") {
            response.body()?.message ?: ""
        } else {
            ""
        }
    }


    fun getImagesByBreed(breed: String): Flow<List<String>> = flow {
        val response = apiServices.getImagesByBreed(breed)
        if (response.isSuccessful && response.body()?.status == "success") {
            emit(response.body()?.message ?: emptyList())
        } else {
            emit(emptyList())
        }
    }


    suspend fun getFavoriteDog(): FavoriteDog? {
        return favoriteDogDao.getFirstFavoriteDog()
    }

    fun setFavoriteDogImages(): Flow<List<FavoriteDog>> {
        return favoriteDogDao.getFavoriteImages()
    }


    fun setViewLaterDogImages(): Flow<List<ViewLaterDog>> {
        return  viewLaterDogDao.getViewLaterDogs()
    }


//    suspend fun addViewLaterDog(viewLaterDog: ViewLaterDog) {
//        viewLaterDogDao.insert(viewLaterDog)
//    }
//
//    suspend fun removeViewLaterDog(viewLaterDog: ViewLaterDog) {
//        viewLaterDogDao.delete(viewLaterDog)
//    }

}
