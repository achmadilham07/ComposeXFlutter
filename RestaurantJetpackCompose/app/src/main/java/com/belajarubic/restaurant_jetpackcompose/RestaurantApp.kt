package com.belajarubic.restaurant_jetpackcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.belajarubic.restaurant_jetpackcompose.ui.common.Utils
import com.belajarubic.restaurant_jetpackcompose.ui.navigation.Screen
import com.belajarubic.restaurant_jetpackcompose.ui.screen.account.AccountScreen
import com.belajarubic.restaurant_jetpackcompose.ui.screen.detail.DetailScreen
import com.belajarubic.restaurant_jetpackcompose.ui.screen.favorite.FavoriteScreen
import com.belajarubic.restaurant_jetpackcompose.ui.screen.home.HomeScreen
import com.belajarubic.restaurant_jetpackcompose.ui.screen.splash.SplashScreen


@Composable
fun RestaurantApp(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                navigateToHome = {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToDetail = { restaurantId ->
                    navController.navigate(Screen.DetailRestaurant.createRoute(restaurantId))
                },
                navigateToAccount = {
                    navController.navigate(Screen.Account.route)
                },
                navigateToFavorite = {
                    navController.navigate(Screen.Favorite.route)
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
        composable(Screen.Favorite.route) {
            FavoriteScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateToDetail = { restaurantId ->
                    navController.navigate(Screen.DetailRestaurant.createRoute(restaurantId))
                },
            )
        }
        composable(
            Screen.DetailRestaurant.route,
            listOf(navArgument(Utils.argumentDetailScreen) { type = NavType.StringType }),
        ) {
            val id = it.arguments?.getString(Utils.argumentDetailScreen) ?: ""
            DetailScreen(
                id = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}