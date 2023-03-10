package com.belajarubic.restaurant_jetpackcompose.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import coil.compose.AsyncImage
import com.belajarubic.restaurant_jetpackcompose.R
import com.belajarubic.restaurant_jetpackcompose.di.Injection
import com.belajarubic.restaurant_jetpackcompose.model.Restaurant
import com.belajarubic.restaurant_jetpackcompose.ui.ViewModelFactory
import com.belajarubic.restaurant_jetpackcompose.ui.composable.BackButton
import com.belajarubic.restaurant_jetpackcompose.ui.composable.CircularIndicator
import com.dicoding.jetreward.ui.common.UiState

@Composable
fun DetailScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    id: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {
    var restaurant by rememberSaveable { mutableStateOf(Restaurant()) }
    val context = LocalContext.current

    Scaffold(
        modifier = modifier.testTag(stringResource(id = R.string.restaurant_detail_page)),
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.restaurant_detail_page),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                )
            }, navigationIcon = {
                BackButton(
                    navigateBack = navigateBack,
                )
            }, actions = {
                viewModel.isFavorite.collectAsState(false).value.let { isFavorite ->

                    viewModel.checkRestaurantIsFavorite(id)

                    val icon =
                        if (isFavorite) Icons.Filled.Favorite
                        else Icons.Filled.FavoriteBorder
                    IconButton(
                        onClick = {
                            Log.e("ERROR", "restaurant: $restaurant")
                            if (isFavorite) viewModel.removeRestaurantFromFavorite(restaurant.id)
                            else viewModel.addRestaurantToFavorite(restaurant)

                            viewModel.checkRestaurantIsFavorite(id)
                        },
                        modifier = Modifier.semantics {
                            contentDescription = context.resources.getString(R.string.favorite)
                        }
                    ) {
                        Icon(
                            icon,
                            contentDescription = stringResource(R.string.favorite),
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }
                }
            })
        }) { contentPadding ->
        viewModel.uiState.collectAsState().value.let { state ->
            when (state) {
                is UiState.Loading -> {
                    viewModel.getRestaurantById(id)
                    CircularIndicator()
                }
                is UiState.Success -> {
                    restaurant = state.data
                    LazyColumn(
                        modifier = Modifier.padding(contentPadding)
                    ) {
                        item {
                            AsyncImage(
                                model = restaurant.getPicture(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = restaurant.name,
                                            style = MaterialTheme.typography.h6,
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = restaurant.city,
                                            style = MaterialTheme.typography.body1,
                                        )
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = restaurant.rating.toString(),
                                            style = MaterialTheme.typography.h6,
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Icon(
                                            Icons.Filled.Star,
                                            contentDescription = null,
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(restaurant.description)
                            }

                        }
                    }
                }
                is UiState.Error -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(id = "", navigateBack = {})
}