package com.example.android_practice.data.serializer

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.android_practice.domain.entity.ProfileEntity

class DataSourceProvider(val context: Context) {
    private val Context.profileDataStore: DataStore<ProfileEntity> by dataStore(
        fileName = "profile.pb",
        serializer = ProfileSerializer
    )

    fun provide() = context.profileDataStore
}