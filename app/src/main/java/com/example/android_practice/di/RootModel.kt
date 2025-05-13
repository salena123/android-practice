package com.example.android_practice.di

import DogResponseToEntityMapper
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.android_practice.data.repository.DogsRepository
import com.example.android_practice.domain.entity.ProfileEntity
import com.example.android_practice.domain.repository.IDogsRepository
import com.example.android_practice.domain.repository.IProfileRepository
import com.example.android_practice.listWithDetails.presentation.viewsModel.DetailsViewModel
import com.example.android_practice.listWithDetails.presentation.viewsModel.ListViewModel
import com.example.android_practice.profile.presentation.viewModel.EditProfileViewModel
import com.example.android_practice.profile.presentation.viewModel.FavoritesViewModel
import com.example.android_practice.profile.presentation.viewModel.ProfileViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val rootModule = module {
    single {
        getSharedPrefs(androidApplication())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }

    single {
        getDataStore(androidContext())
    }

    single<IDogsRepository> { DogsRepository(get(), get(), get()) }

    factory { DogResponseToEntityMapper() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { FavoritesViewModel(get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get()) }

    factory<DataStore<ProfileEntity>>(named("profile")) { com.example.android_practice.data.serializer.DataSourceProvider(
        get()
    ).provide() }
    single<IProfileRepository> { com.example.android_practice.data.repository.ProfileRepository() }
}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences("default", Context.MODE_PRIVATE)
}

fun getDataStore(androidContext: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create {
        androidContext.preferencesDataStoreFile("default")
    }
