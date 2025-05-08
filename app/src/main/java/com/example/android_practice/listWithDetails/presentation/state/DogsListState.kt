package com.example.android_practice.listWithDetails.presentation.state

import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity
import com.example.android_practice.listWithDetails.domain.entity.DogType


interface DogsListState {
    val items: List<DogShortEntity>
    val query: String
    val isEmpty: Boolean
    val error: String?
    val isLoading: Boolean
    val hasBadge: Boolean
    val showTypesDialog: Boolean
    val typesVariants: Set<DogType>
    val selectedTypes: Set<DogType>
}