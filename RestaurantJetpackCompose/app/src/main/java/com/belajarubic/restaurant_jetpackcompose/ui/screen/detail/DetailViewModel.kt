package com.belajarubic.restaurant_jetpackcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belajarubic.restaurant_jetpackcompose.data.RestaurantRepository
import com.belajarubic.restaurant_jetpackcompose.model.Restaurant
import com.dicoding.jetreward.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Restaurant>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Restaurant>>
        get() = _uiState

    private val _isFavorite: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    fun getRestaurantById(restaurantId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getRestaurantById(restaurantId))
        }
    }

    fun checkRestaurantIsFavorite(restaurantId: String) {
        viewModelScope.launch {
            repository.checkRestaurantToDatabase(restaurantId)
                .catch {
                    _isFavorite.value = false
                }.collect {
                    _isFavorite.value = it
                }
        }
    }

    fun addRestaurantToFavorite(restaurant: Restaurant) {
        viewModelScope.launch {
            repository.addRestaurantToDatabase(restaurant)
        }
    }

    fun removeRestaurantFromFavorite(restaurantId: String) {
        viewModelScope.launch {
            repository.removeRestaurantToDatabase(restaurantId)
        }
    }
}
