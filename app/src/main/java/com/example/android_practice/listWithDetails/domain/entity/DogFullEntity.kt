package com.example.android_practice.listWithDetails.domain.entity

class DogFullEntity(
    val id: String = "",
    val name: String = "" ,
    val origin: String? = "",
    val temperament: String? = "",
    val lifeSpan: String? = "",
    val breedGroup: String? = "",
    val bredFor: String? = "",
    val image: DogImage? = null,
    val description: String? = "",
)

class DogImage(
    val url: String? = ""
)