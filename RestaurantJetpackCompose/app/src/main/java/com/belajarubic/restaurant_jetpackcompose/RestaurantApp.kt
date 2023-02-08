package com.belajarubic.restaurant_jetpackcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.belajarubic.restaurant_jetpackcompose.ui.navigation.Screen
import com.belajarubic.restaurant_jetpackcompose.ui.screen.DetailScreen
import com.belajarubic.restaurant_jetpackcompose.ui.screen.HomeScreen
import com.belajarubic.restaurant_jetpackcompose.ui.screen.SplashScreen
import com.belajarubic.restaurant_jetpackcompose.ui.screen.account.AccountScreen


@Composable
fun RestaurantApp(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                navController = navController,
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToDetail = { restaurantId ->
                    navController.navigate(Screen.DetailRestaurant.createRoute(restaurantId))
                },
                navigateToAccount = {
                    navController.navigate(Screen.Account.route)
                }
            )
        }
        composable(Screen.Account.route) {
            AccountScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            Screen.DetailRestaurant.route,
            listOf(navArgument("restaurantId") { type = NavType.StringType }),
        ) {
            val id = it.arguments?.getString("restaurantId") ?: ""
            DetailScreen(
                id = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}