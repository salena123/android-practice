package com.example.android_practice.profile.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_practice.domain.repository.IProfileRepository
import com.example.android_practice.profile.presentation.model.state.EditProfileState
import com.example.android_practice.profile.presentation.model.state.ProfileState
import kotlinx.coroutines.launch

class EditProfileViewModel (
    private val repository: IProfileRepository
): ViewModel() {

    private val mutableState = MutableEditProfileState()
    val viewState = mutableState as EditProfileState

    init {
        viewModelScope.launch {
            repository.getProfile()?.let {
                mutableState.name = it.name
                mutableState.photoUri = Uri.parse(it.photoUri)
            }
        }
        mutableState.isNeedShowPermission = true
    }

    fun onNameChanged(name: String) {
        mutableState.name = name
    }

    fun onDoneClicked() {
        viewModelScope.launch {
            repository.setProfile(mutableState.photoUri.toString(), viewState.name)
        }
    }

    fun onAvatarClicked() {
        mutableState.isNeedToShowSelect = true
    }

    fun onSelectDissMiss() {
        mutableState.isNeedToShowSelect = false
    }

    fun onImageSelected(uri: Uri?) {
        uri?.let { mutableState.photoUri = it }
    }

    fun onPermissionClosed() {
        mutableState.isNeedShowPermission = false
    }

    private class MutableEditProfileState : EditProfileState {
        override var name by mutableStateOf("")
        override var photoUri: Uri by mutableStateOf(Uri.EMPTY)
        override var isNeedShowPermission by mutableStateOf(false)
        override var isNeedToShowSelect: Boolean by mutableStateOf(false)
    }
}