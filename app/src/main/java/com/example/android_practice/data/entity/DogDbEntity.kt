package com.example.android_practice.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class DogDbEntity (
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "dogName") val name: String?,
    @ColumnInfo(name = "dogTemperament") val temperament: String?,
    @ColumnInfo(name = "dogType") val dogType: String?,
    @ColumnInfo(name = "dogImage") val image: String?,
)
