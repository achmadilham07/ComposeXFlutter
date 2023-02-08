package com.belajarubic.restaurant_jetpackcompose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.belajarubic.restaurant_jetpackcompose.ui.composable.CircularIndicator
import com.belajarubic.restaurant_jetpackcompose.ui.navigation.Screen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavHostController
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LaunchedEffect(true) {
            delay(3000)
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Ini adalah splash screen")
            Spacer(modifier = Modifier.height(8.dp))
            CircularIndicator()
        }
    }
}