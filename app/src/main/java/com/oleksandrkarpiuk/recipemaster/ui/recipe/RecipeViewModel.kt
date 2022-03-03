package com.oleksandrkarpiuk.recipemaster.ui.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.Ok
import com.oleksandrkarpiuk.recipemaster.api.models.RecipeDomainModel
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeDatabaseMapper
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeMapper
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeSingleMapper
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeSingleModel
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private var _recipe: MutableLiveData<RecipeSingleModel> = MutableLiveData()
    private var _error: MutableLiveData<String> = MutableLiveData("")

    val recipe: LiveData<RecipeSingleModel> = _recipe
    val error: LiveData<String> = _error

    fun downloadRecipe(recipeId: Int) {
        viewModelScope.launch {
            val recipeResult = recipeRepository.getRecipeById(recipeId)
            if(recipeResult is Ok) {
                _recipe.value = recipeResult.value
            } else {
                _error.value = recipeResult.component2()?.message ?: "Something went wrong"
            }
        }
    }

}