package com.belajarubic.restaurant_jetpackcompose.ui.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.belajarubic.restaurant_jetpackcompose.R
import com.belajarubic.restaurant_jetpackcompose.di.Injection
import com.belajarubic.restaurant_jetpackcompose.ui.ViewModelFactory
import com.belajarubic.restaurant_jetpackcompose.ui.composable.CircularIndicator
import com.belajarubic.restaurant_jetpackcompose.ui.composable.EmptyRestaurantList
import com.belajarubic.restaurant_jetpackcompose.ui.composable.RestaurantItem
import com.dicoding.jetreward.ui.common.UiState

@Composable
fun FavoriteScreen(
    navigateBack: () -> Unit,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {
    Scaffold(
        modifier = modifier.testTag(stringResource(id = R.string.favorite_restaurant_page)),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.favorite_restaurant_page),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        color = Color.White,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack,
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button),
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }
                },
            )
        }
    ) { contentPadding ->
        viewModel.getAllRestaurantFromDatabase()
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { state ->
            when (state) {
                is UiState.Loading -> {
                    CircularIndicator()
                }
                is UiState.Success -> {
                    if (state.data.isEmpty()) {
                        EmptyRestaurantList()
                    }
                    LazyColumn(
                        contentPadding = contentPadding,
                        modifier = Modifier.testTag(
                            stringResource(id = R.string.restaurant_list)
                        ),
                    ) {
                        items(state.data, key = { it.id }) { restaurant ->
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