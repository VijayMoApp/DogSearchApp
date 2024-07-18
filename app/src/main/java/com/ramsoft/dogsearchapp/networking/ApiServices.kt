package com.ramsoft.dogsearchapp.networking


import com.ramsoft.dogsearchapp.models.response.DogBreedImagesResponse
import com.ramsoft.dogsearchapp.models.response.DogResponse
import com.ramsoft.dogsearchapp.utils.ApiConstants

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {


    @GET(ApiConstants.GET_ALL_DOG_DATA)
    suspend fun getRandomDogImage(): Response<DogResponse>


    @GET(ApiConstants.GET_BY_BREED_DATA)
    suspend fun getImagesByBreed(@Path("breed") breed: String): Response<DogBreedImagesResponse>

}