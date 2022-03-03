package com.oleksandrkarpiuk.recipemaster.ui.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeSingleMapper
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider

class RecipeViewModelFactory(
    private val recipeRepository: RecipeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeViewModel(recipeRepository) as T
    }
}