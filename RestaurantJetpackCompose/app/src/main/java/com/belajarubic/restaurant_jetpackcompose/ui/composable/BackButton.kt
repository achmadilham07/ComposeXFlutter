package com.belajarubic.restaurant_jetpackcompose.ui.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.belajarubic.restaurant_jetpackcompose.R

@Composable
fun BackButton(
    navigateBack: () -> Unit = {},
) {
    IconButton(
        onClick = { navigateBack() },
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back_button),
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
    }
}