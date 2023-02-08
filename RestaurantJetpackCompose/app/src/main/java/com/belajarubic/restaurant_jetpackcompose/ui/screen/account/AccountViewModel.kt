package com.belajarubic.restaurant_jetpackcompose.ui.screen.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belajarubic.restaurant_jetpackcompose.data.RestaurantRepository
import com.belajarubic.restaurant_jetpackcompose.model.Account
import com.dicoding.jetreward.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AccountViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Account>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Account>>
        get() = _uiState

    fun getAccount() {
        viewModelScope.launch {
            repository.getAccount().catch {

                _uiState.value = UiState.Error(it.message.toString())
            }.collect {

                _uiState.value = UiState.Success(it)
            }
        }
    }
}