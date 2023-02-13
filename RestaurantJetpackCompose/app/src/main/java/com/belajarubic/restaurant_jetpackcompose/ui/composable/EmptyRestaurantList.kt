package com.belajarubic.restaurant_jetpackcompose.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.belajarubic.restaurant_jetpackcompose.R

@Composable
fun EmptyRestaurantList(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxHeight()
            .semantics {
                contentDescription = context.resources.getString(R.string.empty_restaurant_item)
            },
    ) {
        Text(text = stringResource(id = R.string.no_restaurant_item))
    }
}