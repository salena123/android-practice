package com.example.android_practice.data.api

import com.example.android_practice.data.model.DogBreed
import com.example.android_practice.data.model.DogFullListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApiService {

    @GET("v1/breeds")
    suspend fun getAllBreeds(): List<DogBreed>

    @GET("v1/breeds/search")
    suspend fun searchBreeds(
        @Query("q") breedName: String? = null,
    ): List<DogBreed>

    @GET("v1/breeds/search")
    suspend fun getBreedByName(
        @Query("q") breedName: String? = null
    ): List<DogFullListResponse>

}


