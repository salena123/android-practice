package com.example.android_practice.listWithDetails.data.repository

import com.example.android_practice.listWithDetails.data.mock.DogsData
import com.example.android_practice.listWithDetails.domain.repository.IDogsRepository


class DogsRepository: IDogsRepository {
    override fun getList(q: String) = DogsData.dogsShortEntity.filter { it.name.contains(q, ignoreCase = true) }

    override fun getById(id: String) = DogsData.dogsFullEntity.find { it.id == id}
}