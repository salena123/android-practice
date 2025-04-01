package com.example.android_practice.listWithDetails.data.model

import androidx.annotation.Keep
import com.example.android_practice.listWithDetails.domain.entity.DogImage
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
    @SerializedName("lifeSpan")
    val lifeSpan: String?,
    @SerializedName("breedGroup")
    val breedGroup: String?,
    @SerializedName("bredFor")
    val bredFor: String?,
    @SerializedName("image")
    val image: DogImage?,
    @SerializedName("description")
    val description: String?
) : DogApiBase()

class DogImageResponse(
    @SerializedName("url")
    val url: String?
)


