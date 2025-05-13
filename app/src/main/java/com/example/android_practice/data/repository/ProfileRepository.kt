package com.example.android_practice.data.repository

import androidx.datastore.core.DataStore
import com.example.android_practice.domain.entity.ProfileEntity
import com.example.android_practice.domain.repository.IProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class ProfileRepository : IProfileRepository {
    private val dataStore : DataStore<ProfileEntity> by inject(DataStore::class.java, named("profile"))

    override suspend fun getProfile(): ProfileEntity? = dataStore.data.firstOrNull()

    override suspend fun setProfile(phoroUri: String, name: String, /*url: String*/): ProfileEntity =
        dataStore.updateData {
            it.toBuilder().apply {
                this.photoUri = phoroUri
                this.name = name
//                this.url = url
            }.build()
        }

    override suspend fun observeProfile(): Flow<ProfileEntity> = dataStore.data

}
