package com.belajarubic.restaurant_jetpackcompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.belajarubic.restaurant_jetpackcompose.data.RestaurantRepository
import com.belajarubic.restaurant_jetpackcompose.ui.screen.account.AccountViewModel
import com.belajarubic.restaurant_jetpackcompose.ui.screen.detail.DetailViewModel
import com.belajarubic.restaurant_jetpackcompose.ui.screen.favorite.FavoriteViewModel
import com.belajarubic.restaurant_jetpackcompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: RestaurantRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}