package com.example.android_practice.listWithDetails.domain.repository

import com.example.android_practice.listWithDetails.domain.entity.DogFullEntity
import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity

interface IDogsRepository {
    suspend fun getList(q: String =""): List<DogShortEntity>

    suspend fun getByName(name: String): DogFullEntity?
}