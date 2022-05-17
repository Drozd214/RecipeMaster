package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeItemMapper
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider

class HomeViewModelFactory(
    private val recipeRepository: RecipeRepository,
    private val stringProvider: StringProvider,
    private val recipeItemMapper: RecipeItemMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(recipeRepository, stringProvider, recipeItemMapper) as T
    }

}