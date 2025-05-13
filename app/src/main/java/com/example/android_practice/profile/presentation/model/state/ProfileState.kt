package com.example.android_practice.profile.presentation.model.state

import android.net.Uri

interface ProfileState {
    val name: String
    val photoUri: Uri
}