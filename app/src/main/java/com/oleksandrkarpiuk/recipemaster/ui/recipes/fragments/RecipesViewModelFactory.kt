package com.oleksandrkarpiuk.recipemaster.ui.recipes.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.ui.recipes.RecipesContainerViewModel
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider

class RecipesViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipesViewModel() as T
    }

}