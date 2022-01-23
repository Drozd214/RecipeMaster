package com.oleksandrkarpiuk.recipemaster.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.HomeViewModel

class MainViewModelFactory(private val recipeRepository: RecipeRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(recipeRepository) as T
    }

}