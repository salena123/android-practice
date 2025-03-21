package com.example.android_practice.listWithDetails.domain.repository

import com.example.android_practice.listWithDetails.domain.entity.DogFullEntity
import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity

interface IDogsRepository {
    fun getList(q: String =""): List<DogShortEntity>

    fun getById(id: String): DogFullEntity?
}