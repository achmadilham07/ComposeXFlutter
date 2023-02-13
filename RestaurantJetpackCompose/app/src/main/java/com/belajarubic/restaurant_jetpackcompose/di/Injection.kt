package com.belajarubic.restaurant_jetpackcompose.di

import com.belajarubic.restaurant_jetpackcompose.data.RestaurantRepository

object Injection {
    fun provideRepository(): RestaurantRepository {
        return RestaurantRepository.getInstance()
    }
}