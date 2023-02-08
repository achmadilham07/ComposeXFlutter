package com.belajarubic.restaurant_jetpackcompose.ui.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CircularIndicator(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        modifier = modifier.size(64.dp),
        color = MaterialTheme.colors.primary,
        strokeWidth = 6.dp,
    )
}