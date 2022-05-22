package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleksandrkarpiuk.recipemaster.usecases.recipe.GetFavouriteRecipesUseCase

class FavouritesViewModelFactory(
    private val getFavouriteRecipesUseCase: GetFavouriteRecipesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavouritesViewModel(getFavouriteRecipesUseCase) as T
    }

}