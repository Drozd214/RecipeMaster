package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.favourites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeSingleModel
import com.oleksandrkarpiuk.recipemaster.usecases.recipe.GetFavouriteRecipesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class FavouritesViewModel(
    private val getFavouriteRecipesUseCase: GetFavouriteRecipesUseCase
) : ViewModel() {

    private var _favouriteRecipes: MutableLiveData<List<RecipeSingleModel>> = MutableLiveData(listOf())
    val favouriteRecipes: LiveData<List<RecipeSingleModel>> = _favouriteRecipes

    fun loadData() {
        getFavouriteRecipesUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ recipes ->
                _favouriteRecipes.value = recipes
                this.favouriteRecipes.value?.forEach { recipe ->
                    Log.e("Batman", recipe.toString()) }
            }, { error ->
                Log.e("Batman", "Error is $error")
            })
    }

}