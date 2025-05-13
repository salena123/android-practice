package com.example.android_practice.favorites.state

import com.example.android_practice.domain.entity.DogShortEntity

data class FavoritesViewState(
    val items: List<DogShortEntity> = emptyList()
)