package com.example.android_practice.profile.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_practice.favorites.state.FavoritesViewState
import com.example.android_practice.domain.repository.IDogsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: IDogsRepository
): ViewModel() {

    private var mutableState = MutableStateFlow(FavoritesViewState())
    val viewState = mutableState.asStateFlow()

    init {
        updateFavorites()
    }

    fun onUpdateClick() {
        updateFavorites()
    }

    private fun updateFavorites() {
        viewModelScope.launch {
            mutableState.update { it.copy(items = repository.getFavorites()) }
        }
    }
}