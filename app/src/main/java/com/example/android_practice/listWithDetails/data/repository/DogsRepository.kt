package com.example.android_practice.listWithDetails.data.repository

import DogResponseToEntityMapper
import com.example.android_practice.listWithDetails.data.api.DogApiService
import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity
import com.example.android_practice.listWithDetails.domain.repository.IDogsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DogsRepository(
    private val api: DogApiService,
    private val mapper: DogResponseToEntityMapper
) : IDogsRepository {

    override suspend fun getList(q: String): List<DogShortEntity> =
        withContext(Dispatchers.IO) {
            if (q.isBlank()) {
                val response = api.getAllBreeds()
                mapper.mapBreedList(response)
            } else {
                val response = api.searchBreeds(q)
                mapper.mapSearchBreeds(response)
            }
        }

    override suspend fun getByName(name: String) =
        withContext(Dispatchers.IO) {
            val response = api.searchBreeds(name)
            val breed = response.firstOrNull()
            breed?.let {
                val fullBreedResponse = api.getBreedByName(it.name ?: "")
                mapper.mapBreedFull(fullBreedResponse)
            }
        }
}

