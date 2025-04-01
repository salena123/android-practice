package com.example.android_practice.listWithDetails.presentation.viewsModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity
import com.example.android_practice.listWithDetails.domain.repository.IDogsRepository
import com.example.android_practice.listWithDetails.presentation.screens.DetailsScreen
import com.example.android_practice.listWithDetails.presentation.state.DogsListState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

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

        viewModelScope.launch {
            try {
                mutableState.isLoading = true
                mutableState.error = null
                mutableState.items = repository.getList(viewState.query)
            }  catch (e: IOException) {
                mutableState.error = "Проблемы с интернетом"
            } catch (e: HttpException) {
                when (e.code()) {
                    400 -> mutableState.error = "Неверный запрос"
                    401 -> mutableState.error = "Ошибка авторизации"
                    404 -> mutableState.error = "Собака не найдена"
                    500 -> mutableState.error = "Ошибка сервера, попробуйте позже"
                    else -> mutableState.error = "Ошибка: ${e.code()}"
                }
            } catch (e: Exception) {
                mutableState.error = e.localizedMessage
            } finally {
                mutableState.isLoading = false
            }

        }

    }

    fun onItemClicked(name: String) {
        navigation.forward(DetailsScreen(dogName = name))
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        loadDogs()
    }

    private class MutableDogsListState: DogsListState {
        override var items: List<DogShortEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
        override var error: String? by mutableStateOf(null)
        override var isLoading: Boolean by mutableStateOf(false)
    }
}