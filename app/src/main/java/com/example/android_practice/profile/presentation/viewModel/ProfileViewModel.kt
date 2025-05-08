package com.example.android_practice.profile.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_practice.listWithDetails.domain.repository.IDogsRepository
import com.example.android_practice.profile.presentation.state.ProfileViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: IDogsRepository
): ViewModel() {

    private var mutableState = MutableStateFlow(ProfileViewState())
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