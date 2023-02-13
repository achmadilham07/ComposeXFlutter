package com.belajarubic.restaurant_jetpackcompose.utils

import com.belajarubic.restaurant_jetpackcompose.R
import com.belajarubic.restaurant_jetpackcompose.model.Account
import com.belajarubic.restaurant_jetpackcompose.model.Restaurant

object DummyData {
    val account = Account(
        name = "Achmad Ilham",
        email = "achmadilham07@gmail.com",
        imageCategory = R.drawable.img,
    )

    val restaurant: Restaurant = Restaurant(
        "s1knt6za9kkfw1e867",
        "Kafe Kita",
        "Kafe Kita adalah sebuah kafe yang menyediakan berbagai macam minuman dan makanan yang lezat. Tempat ini memiliki suasana yang nyaman dan santai, cocok untuk bersantai bersama teman-teman atau untuk bekerja. Kafe ini juga menyediakan akses internet gratis dan beberapa permainan untuk hiburan. Pelayanan yang diberikan juga cepat dan ramah. Kafe Kita adalah tempat yang sempurna untuk menikmati waktu luang atau untuk kerja produktif.",
        "25",
        "Gorontalo",
        4.0
    )

    val restaurantList = listOf(
        restaurant
    )
}