package com.example.android_practice.listWithDetails.presentation.state

import com.example.android_practice.listWithDetails.domain.entity.DogFullEntity

interface DogDetailState {
    val dog: DogFullEntity?
    val likes: Int
    val isLiked: Boolean
    var isLoading: Boolean
    var error: String?
}