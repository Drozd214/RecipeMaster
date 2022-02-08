package com.oleksandrkarpiuk.recipemaster.ui.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider

class RecipesViewModelFactory(
    private val recipesRepository: RecipeRepository,
    private val stringProvider: StringProvider
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipesViewModel(recipesRepository, stringProvider) as T
    }
}