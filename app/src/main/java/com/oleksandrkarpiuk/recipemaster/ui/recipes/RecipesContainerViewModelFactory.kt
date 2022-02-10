package com.oleksandrkarpiuk.recipemaster.ui.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider

class RecipesContainerViewModelFactory(
    private val recipesRepository: RecipeRepository,
    private val stringProvider: StringProvider
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipesContainerViewModel(recipesRepository, stringProvider) as T
    }
}