package com.example.android_practice.listWithDetails.data.repository

import DogResponseToEntityMapper
import com.example.android_practice.listWithDetails.data.api.DogApiService
import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity
import com.example.android_practice.listWithDetails.domain.entity.DogFullEntity
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

    override suspend fun getByName(name: String): List<DogFullEntity> =
        withContext(Dispatchers.IO) {
            val response = api.getBreedByName(name)
            mapper.mapBreedFull(response)
        }
}

