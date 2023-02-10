package com.belajarubic.restaurant_jetpackcompose.ui.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.belajarubic.restaurant_jetpackcompose.R


@Composable
fun CircularIndicator(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        modifier = modifier
            .size(64.dp)
            .testTag(stringResource(id = R.string.loading)),
        color = MaterialTheme.colors.primary,
        strokeWidth = 6.dp,
    )
}