package com.example.android_practice.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_practice.data.dao.DogDao
import com.example.android_practice.data.entity.DogDbEntity

@Database(entities = [DogDbEntity::class], version = 1)
abstract class DogDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao
}