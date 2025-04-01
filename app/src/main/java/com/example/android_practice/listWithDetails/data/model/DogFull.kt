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
    @SerializedName("life_span")
    val lifeSpan: String?,
    @SerializedName("breed_group")
    val breedGroup: String?,
    @SerializedName("bred_for")
    val bredFor: String?,
    @SerializedName("reference_image_id")
    val referenceImageId: String?,
    @SerializedName("description")
    val description: String?,
) : DogApiBase()

class DogImageResponse(
    @SerializedName("url")
    val url: String?
)


