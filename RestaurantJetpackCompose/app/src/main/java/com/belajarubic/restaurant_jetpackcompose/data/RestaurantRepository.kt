package com.belajarubic.restaurant_jetpackcompose.data

import com.belajarubic.restaurant_jetpackcompose.model.Account
import com.belajarubic.restaurant_jetpackcompose.model.FakeRestaurantDataSource
import com.belajarubic.restaurant_jetpackcompose.model.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RestaurantRepository {

    private val restaurant = mutableListOf<Restaurant>()
    private val localRestaurant = mutableListOf<Restaurant>()
    private var account: Account

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

    fun getRestaurantByQuery(query: String): List<Restaurant> {
        return restaurant.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun addRestaurantToDatabase(restaurant: Restaurant): Flow<Boolean> {
        return flowOf(localRestaurant.add(restaurant))
    }

    fun removeRestaurantToDatabase(restaurantId: String): Flow<Boolean> {
        val restaurant = localRestaurant.find {
            it.id == restaurantId
        }
        return flowOf(localRestaurant.remove(restaurant))
    }

    fun getAllRestaurantListFromDatabase(): Flow<List<Restaurant>> {
        return flowOf(localRestaurant)
    }

    fun checkRestaurantToDatabase(restaurantId: String): Flow<Boolean> {
        val restaurant = localRestaurant.find {
            it.id == restaurantId
        }
        return flowOf(restaurant != null)
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