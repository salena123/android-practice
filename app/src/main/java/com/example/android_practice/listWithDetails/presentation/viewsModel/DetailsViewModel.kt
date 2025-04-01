package com.example.android_practice.listWithDetails.presentation.viewsModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_practice.core.coroutinesUtils.launchLoadingAndError
import com.example.android_practice.listWithDetails.domain.entity.DogFullEntity
import com.example.android_practice.listWithDetails.domain.repository.IDogsRepository
import com.example.android_practice.listWithDetails.presentation.state.DogDetailState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back
import kotlinx.coroutines.async

class DetailsViewModel(
    private val repository: IDogsRepository,
    private val navigation: StackNavContainer,
    private val name: String,
): ViewModel() {

    private val mutableState by mutableStateOf(MutableDetailsState())
    val viewState = mutableState as DogDetailState

    init {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it },
        ) {
            val dogsDeferred = async { repository.getByName(name)}
            val dogs = dogsDeferred.await()
            mutableState.dog = dogs.firstOrNull()
        }
    }

    fun back() {
        navigation.back()
    }
    fun onLikeCounterChanged() {
        mutableState.toggleLike()
    }

    private class MutableDetailsState: DogDetailState {
        override var dog: DogFullEntity? by mutableStateOf(null)
        override var likes: Int by mutableStateOf(0)
        override var isLiked: Boolean by mutableStateOf(false)
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)

        fun toggleLike() {
            if (isLiked) {
                likes --
            } else {
                likes ++
            }
            isLiked = !isLiked
        }
    }
}