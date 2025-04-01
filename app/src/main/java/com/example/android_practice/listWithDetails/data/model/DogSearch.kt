package com.example.android_practice.listWithDetails.data.model

import com.example.android_practice.listWithDetails.domain.entity.DogImage
import com.google.gson.annotations.SerializedName


class DogBreedListResponse(
    @SerializedName("breeds")
    val breeds: List<DogBreed>?
) : DogApiBase()

class DogBreed(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("temperament")
    val temperament: String?,
    @SerializedName("image")
    val image: DogImageResponse?
)
