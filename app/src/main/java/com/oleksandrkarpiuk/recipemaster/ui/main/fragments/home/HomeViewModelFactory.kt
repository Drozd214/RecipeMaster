package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleksandrkarpiuk.recipemaster.ui.main.MainViewModel

class HomeViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel() as T
    }

}