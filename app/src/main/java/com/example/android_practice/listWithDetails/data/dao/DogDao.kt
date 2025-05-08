package com.example.android_practice.listWithDetails.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.android_practice.listWithDetails.data.entity.DogDbEntity

@Dao
interface DogDao {
    @Query("SELECT * FROM DogDbEntity")
    suspend fun getAll(): List<DogDbEntity>

    @Insert
    suspend fun insert(driverDbEntity: DogDbEntity)
}