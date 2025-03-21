package com.example.android_practice.listWithDetails.data.repository

import com.example.android_practice.listWithDetails.data.mock.DogsData

class DogsRepository {
    fun getList(q: String ="") = DogsData.dogsShortEntity.filter { it.name.contains(q, ignoreCase = true) }

    fun getById(id: String) = DogsData.dogsFullEntity.find { it.id == id}
}