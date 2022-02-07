package com.oleksandrkarpiuk.recipemaster.ui.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository

class RecipesViewModelFactory(
    private val recipesRepository: RecipeRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipesViewModel(recipesRepository) as T
    }
}