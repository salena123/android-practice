package com.example.android_practice.profile.presentation.model.state

import android.net.Uri

interface EditProfileState {
    val name: String
    val photoUri: Uri
    var isNeedShowPermission: Boolean
    var isNeedToShowSelect: Boolean
}