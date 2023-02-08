package com.belajarubic.restaurant_jetpackcompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    var id: String,
    var name: String,
    var description: String,
    var pictureId: String,
    var city: String,
    var rating: Double
) : Parcelable {
    fun getPicture(): String {
        return "https://restaurant-api.dicoding.dev/images/medium/$pictureId"
    }
}