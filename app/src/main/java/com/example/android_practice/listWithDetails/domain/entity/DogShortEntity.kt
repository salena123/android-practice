package com.example.android_practice.listWithDetails.domain.entity

import androidx.annotation.StringRes
import com.example.android_practice.R

class DogShortEntity(
    val id: String = "",
    val name: String = "",
    val temperament: String? = "",
    val image: String? = "",
    val dogType: DogType = DogType.OTHER,
)

enum class DogType(@StringRes val stringRes: Int) {
    HOUND(R.string.hound),
    TOY(R.string.toy),
    OTHER(R.string.other);

    companion object {
        fun getByValue(type: String?) = entries.find { it.name.equals(type, ignoreCase = true) } ?: OTHER
    }
}