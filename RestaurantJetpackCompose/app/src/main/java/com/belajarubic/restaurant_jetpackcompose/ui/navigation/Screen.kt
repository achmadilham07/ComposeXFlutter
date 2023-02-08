package com.belajarubic.restaurant_jetpackcompose.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Account : Screen("account")
    object DetailRestaurant : Screen("home/{restaurantId}") {
        fun createRoute(restaurantId: String) = "home/$restaurantId"
    }
}