package com.belajarubic.restaurant_jetpackcompose.data

import com.belajarubic.restaurant_jetpackcompose.model.Account
import com.belajarubic.restaurant_jetpackcompose.model.FakeRestaurantDataSource
import com.belajarubic.restaurant_jetpackcompose.model.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RestaurantRepository {

    private val restaurant = mutableListOf<Restaurant>()
    private lateinit var account: Account

    init {
        if (restaurant.isEmpty()) {
            FakeRestaurantDataSource.dummyRestaurant.forEach {
                restaurant.add(it)
            }
        }
        account = FakeRestaurantDataSource.dummyAccount
    }

    fun getAccount(): Flow<Account> {
        return flowOf(account)
    }

    fun getAllRestaurantList(): Flow<List<Restaurant>> {
        return flowOf(restaurant)
    }

    fun getRestaurantById(restaurantId: String): Restaurant {
        return restaurant.first {
            it.id == restaurantId
        }
    }

    companion object {
        @Volatile
        private var instance: RestaurantRepository? = null

        fun getInstance(): RestaurantRepository =
            instance ?: synchronized(this) {
                RestaurantRepository().apply {
                    instance = this
                }
            }
    }
}