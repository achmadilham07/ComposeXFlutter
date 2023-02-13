package com.belajarubic.restaurant_jetpackcompose.model

import androidx.annotation.DrawableRes

data class Account(
    @DrawableRes val imageCategory: Int,
    val name: String,
    val email: String,
)