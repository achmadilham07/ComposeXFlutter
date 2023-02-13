package com.belajarubic.restaurant_jetpackcompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.belajarubic.restaurant_jetpackcompose.R
import com.belajarubic.restaurant_jetpackcompose.di.Injection
import com.belajarubic.restaurant_jetpackcompose.ui.ViewModelFactory
import com.belajarubic.restaurant_jetpackcompose.ui.composable.CircularIndicator
import com.belajarubic.restaurant_jetpackcompose.ui.composable.EmptyRestaurantList
import com.belajarubic.restaurant_jetpackcompose.ui.composable.RestaurantItem
import com.belajarubic.restaurant_jetpackcompose.ui.composable.SearchBar
import com.dicoding.jetreward.ui.common.UiState

@Composable
fun HomeScreen(
    navigateToDetail: (String) -> Unit,
    navigateToAccount: () -> Unit,
    navigateToFavorite: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {
    val query by viewModel.query
    val context = LocalContext.current

    Scaffold(
        modifier = modifier.testTag(stringResource(id = R.string.restaurant_list_page)),
        topBar = {
            TopAppBar(
                actions = {
                    IconButton(
                        onClick = navigateToAccount,
                        modifier = Modifier.semantics {
                            contentDescription = context.resources.getString(R.string.account)
                        }
                    ) {
                        Icon(Icons.Filled.Person, "about_page")
                    }
                    IconButton(
                        onClick = navigateToFavorite,
                        modifier = Modifier.semantics {
                            contentDescription = context.resources.getString(R.string.favorite)
                        }
                    ) {
                        Icon(Icons.Filled.Favorite, "favorite_page")
                    }
                },
                elevation = 0.dp,
                title = {
                    Text(
                        text = stringResource(id = R.string.restaurant_list_page),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        color = Color.White,
                    )
                }
            )
        }
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier.background(MaterialTheme.colors.primary)
            ) {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::searchRestaurant,
                    modifier = Modifier.testTag(stringResource(id = R.string.search_bar))
                )
            }
            viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { state ->
                when (state) {
                    is UiState.Loading -> {
                        viewModel.getAllRestaurant()
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
                            )
                        ) {
                            items(state.data, key = { it.id }) { restaurant ->
                                RestaurantItem(
                                    restaurant = restaurant,
                                    modifier = Modifier.clickable {
                                        navigateToDetail(restaurant.id)
                                    }
                                )
                            }
                        }
                    }
                    is UiState.Error -> {}
                }
            }

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigateToDetail = {},
        navigateToAccount = {},
        navigateToFavorite = {}
    )
}