package com.example.android_practice.listWithDetails.presentation.viewsModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity
import com.example.android_practice.listWithDetails.domain.repository.IDogsRepository
import com.example.android_practice.listWithDetails.presentation.screens.DetailsScreen
import com.example.android_practice.listWithDetails.presentation.state.DogsListState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward

class ListViewModel(
    private val repository: IDogsRepository,
    private val navigation: StackNavContainer
): ViewModel() {

    private  val mutableState = MutableDogsListState()
    val viewState = mutableState as DogsListState

    init {
        loadDogs()
    }

    private fun loadDogs() {
        mutableState.items = repository.getList(viewState.query)
    }

    fun onItemClicked(id: String) {
        navigation.forward(DetailsScreen(dogId = id))
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        loadDogs()
    }

    private class MutableDogsListState: DogsListState {
        override var items: List<DogShortEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
    }
}