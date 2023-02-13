package com.belajarubic.restaurant_jetpackcompose.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belajarubic.restaurant_jetpackcompose.data.RestaurantRepository
import com.belajarubic.restaurant_jetpackcompose.model.Restaurant
import com.dicoding.jetreward.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Restaurant>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Restaurant>>>
        get() = _uiState

    fun getAllRestaurantFromDatabase() {
        viewModelScope.launch {
            repository.getAllRestaurantListFromDatabase()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }

}