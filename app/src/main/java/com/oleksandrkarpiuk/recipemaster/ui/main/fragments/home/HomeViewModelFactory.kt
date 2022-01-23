package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository

class HomeViewModelFactory(private val recipeRepository: RecipeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(recipeRepository) as T
    }

}