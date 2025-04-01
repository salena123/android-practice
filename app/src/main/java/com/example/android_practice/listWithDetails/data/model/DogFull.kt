package com.example.android_practice.listWithDetails.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class DogFullListResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin")
    val origin: String?,
    @SerializedName("temperament")
    val temperament: String?,
    @SerializedName("life_span")
    val lifeSpan: String?,
    @SerializedName("breed_group")
    val breedGroup: String?,
    @SerializedName("bred_for")
    val bredFor: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image")
    val image: DogImageResponse?
) : DogApiBase()


