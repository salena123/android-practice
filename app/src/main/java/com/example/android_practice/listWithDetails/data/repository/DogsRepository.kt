package com.example.android_practice.listWithDetails.data.repository

import DogResponseToEntityMapper
import com.example.android_practice.listWithDetails.data.api.DogApiService
import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity
import com.example.android_practice.listWithDetails.domain.entity.DogFullEntity
import com.example.android_practice.listWithDetails.domain.entity.DogType
import com.example.android_practice.listWithDetails.domain.repository.IDogsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DogsRepository(
    private val api: DogApiService,
    private val mapper: DogResponseToEntityMapper
) : IDogsRepository {

    override suspend fun getList(q: String, filterTypes: Set<DogType>?): List<DogShortEntity> =
        withContext(Dispatchers.IO) {
            if (q.isBlank()) {
                val response = api.getAllBreeds()
                val mapped = mapper.mapBreedList(response)
                filterTypes?.takeIf { it.isNotEmpty() }?.let { filters ->
                    mapped.filter { it.dogType in filters }
                } ?: mapped
            } else {
                val response = api.searchBreeds(q)
                val mapped = mapper.mapSearchBreeds(response)
                filterTypes?.takeIf { it.isNotEmpty() }?.let { filters ->
                    mapped.filter { it.dogType in filters }
                } ?: mapped
            }
        }
    override suspend fun getByName(name: String): List<DogFullEntity> =
        withContext(Dispatchers.IO) {
            val response = api.getBreedByName(name)
            mapper.mapBreedFull(response)
        }
    }


