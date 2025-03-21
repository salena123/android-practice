package com.example.android_practice.listWithDetails.presentation.state

import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity

interface DogsListState {
    val items: List<DogShortEntity>
    val query: String
    val isEmpty: Boolean
}