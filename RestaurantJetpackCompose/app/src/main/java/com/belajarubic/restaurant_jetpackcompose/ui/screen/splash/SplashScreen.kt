package com.belajarubic.restaurant_jetpackcompose.ui.screen.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.belajarubic.restaurant_jetpackcompose.R
import com.belajarubic.restaurant_jetpackcompose.ui.common.Utils
import com.belajarubic.restaurant_jetpackcompose.ui.composable.CircularIndicator
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .testTag(stringResource(id = R.string.splash_page))
    ) {
        LaunchedEffect(true) {
            delay(Utils.delaySplashScreen)
            navigateToHome()
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(id = R.string.splash_text))
            Spacer(modifier = Modifier.height(8.dp))
            CircularIndicator()
        }
    }
}