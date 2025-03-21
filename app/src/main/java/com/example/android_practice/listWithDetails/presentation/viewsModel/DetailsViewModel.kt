package com.example.android_practice.listWithDetails.presentation.viewsModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android_practice.listWithDetails.domain.entity.DogFullEntity
import com.example.android_practice.listWithDetails.domain.repository.IDogsRepository
import com.example.android_practice.listWithDetails.presentation.state.DogDetailState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back

class DetailsViewModel(
    private val repository: IDogsRepository,
    private val navigation: StackNavContainer,
    private val id: String
): ViewModel() {

    private var mustableState by mutableStateOf(MutableDetailsState())
    val viewState = mustableState as DogDetailState

    init {
        mustableState.dog = repository.getById(id)
    }

    fun back() {
        navigation.back()
    }
    fun onLikeCounterChanged() {
        mustableState.toggleLike()
    }

    private class MutableDetailsState: DogDetailState {
        override var dog: DogFullEntity? by mutableStateOf(null)
        override var likes: Int by mutableStateOf(0)
        override var isLiked: Boolean by mutableStateOf(false)

        fun toggleLike() {
            if (isLiked) {
                likes -= 1
            } else {
                likes += 1
            }
            isLiked = !isLiked
        }
    }
}