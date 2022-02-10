package com.oleksandrkarpiuk.recipemaster.ui.recipes.fragments

import androidx.lifecycle.ViewModel
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider

class RecipesViewModel(
    private val recipesRepository: RecipeRepository,
    private val stringProvider: StringProvider
) : ViewModel() {

}