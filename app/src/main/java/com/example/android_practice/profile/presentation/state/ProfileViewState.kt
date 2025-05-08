package com.example.android_practice.profile.presentation.state

import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity

data class ProfileViewState(
    val items: List<DogShortEntity> = emptyList()
)
