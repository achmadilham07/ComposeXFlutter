package com.belajarubic.restaurant_jetpackcompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val id: String,
    val name: String,
    val description: String,
    val pictureId: String,
    val city: String,
    val rating: Double
) : Parcelable {

    constructor() : this(
        "", "", "", "", "", 0.0
    )

    fun getPicture(): String {
        return "https://restaurant-api.dicoding.dev/images/medium/$pictureId"
    }
}