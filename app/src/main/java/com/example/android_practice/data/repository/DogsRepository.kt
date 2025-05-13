package com.example.android_practice.data.repository

import DogResponseToEntityMapper
import com.example.android_practice.data.api.DogApiService
import com.example.android_practice.data.database.DogDatabase
import com.example.android_practice.data.entity.DogDbEntity
import com.example.android_practice.domain.entity.DogShortEntity
import com.example.android_practice.domain.entity.DogFullEntity
import com.example.android_practice.domain.entity.DogType
import com.example.android_practice.domain.repository.IDogsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DogsRepository(
    private val api: DogApiService,
    private val mapper: DogResponseToEntityMapper,
    private val db: DogDatabase
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

    override suspend fun saveFavorite(dog: DogShortEntity) =
        withContext(Dispatchers.IO) {
            db.dogDao().insert(
                DogDbEntity(
                    name = dog.name,
                    temperament = dog.temperament,
                    image = dog.image,
                    dogType = dog.dogType.name,

                )
            )
        }

    override suspend fun getFavorites() =
        withContext(Dispatchers.IO) {
            db.dogDao().getAll().map {
                DogShortEntity(
                    it.id.toString(),
                    it.name.orEmpty(),
                    it.temperament.orEmpty(),
                    it.image.orEmpty(),
                    DogType.getByValue(it.dogType),
                )
            }
        }
    }


