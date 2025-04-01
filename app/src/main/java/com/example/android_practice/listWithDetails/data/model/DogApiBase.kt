package com.example.android_practice.listWithDetails.data.model

import com.google.gson.annotations.SerializedName

abstract class DogApiBase {
    @SerializedName("Response")
    val response: Boolean = false

    @SerializedName("Error")
    val error: String? = null
}