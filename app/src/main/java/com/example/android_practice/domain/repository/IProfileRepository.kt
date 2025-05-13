package com.example.android_practice.domain.repository

import com.example.android_practice.domain.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {
    suspend fun getProfile(): ProfileEntity?
    suspend fun setProfile(phoroUri: String, name: String/*, url: String*/): ProfileEntity
    suspend fun observeProfile(): Flow<ProfileEntity>
}