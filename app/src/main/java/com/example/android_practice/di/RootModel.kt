package com.example.android_practice.di

import DogResponseToEntityMapper

import com.example.android_practice.listWithDetails.data.repository.DogsRepository
import com.example.android_practice.listWithDetails.domain.repository.IDogsRepository
import com.example.android_practice.listWithDetails.presentation.viewsModel.DetailsViewModel
import com.example.android_practice.listWithDetails.presentation.viewsModel.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single<IDogsRepository> { DogsRepository(get(), get()) }

    factory { DogResponseToEntityMapper() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
}