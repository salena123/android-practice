package com.example.android_practice.domain.repository

import com.example.android_practice.domain.entity.DogFullEntity
import com.example.android_practice.domain.entity.DogShortEntity
import com.example.android_practice.domain.entity.DogType

interface IDogsRepository {
    suspend fun getList(
        q: String ="",
        filterTypes: Set<DogType>? = null
    ): List<DogShortEntity>

    suspend fun getByName(name: String): List<DogFullEntity>

    suspend fun getFavorites(): List<DogShortEntity>
    suspend fun saveFavorite(dog: DogShortEntity)
}