package com.belajarubic.restaurant_jetpackcompose.ui.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.belajarubic.restaurant_jetpackcompose.di.Injection
import com.belajarubic.restaurant_jetpackcompose.ui.ViewModelFactory
import com.belajarubic.restaurant_jetpackcompose.ui.composable.CircularIndicator
import com.belajarubic.restaurant_jetpackcompose.ui.composable.RestaurantItem
import com.belajarubic.restaurant_jetpackcompose.ui.navigation.Screen
import com.dicoding.jetreward.ui.common.UiState
import kotlinx.coroutines.delay

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit = {},
    navigateToDetail: (String) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Restaurant Detail",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                color = Color.White,
            )
        }, navigationIcon = {
            IconButton(
                onClick = { navigateBack() },
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        })
    }) { contentPadding ->
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { state ->
            when (state) {
                is UiState.Loading -> {
                    viewModel.getAllRestaurantFromDatabase()
                    CircularIndicator()
                }
                is UiState.Success -> {
                    if (state.data.isEmpty()) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = "Tidak ada restaurant",
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                    LazyColumn(contentPadding = contentPadding) {
                        items(state.data) { restaurant ->
                            RestaurantItem(
                                restaurant = restaurant,
                                modifier = Modifier.clickable {
                                    navigateToDetail(restaurant.id)
                                })
                        }
                    }
                }
                is UiState.Error -> {}
            }
        }
    }
}