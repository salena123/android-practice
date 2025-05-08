package com.example.android_practice.listWithDetails.domain.repository

import com.example.android_practice.listWithDetails.domain.entity.DogFullEntity
import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity
import com.example.android_practice.listWithDetails.domain.entity.DogType

interface IDogsRepository {
    suspend fun getList(
        q: String ="",
        filterTypes: Set<DogType>? = null
    ): List<DogShortEntity>

    suspend fun getByName(name: String): List<DogFullEntity>
}