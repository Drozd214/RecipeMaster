package com.oleksandrkarpiuk.recipemaster.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.map
import com.oleksandrkarpiuk.recipemaster.api.models.Recipe
import com.oleksandrkarpiuk.recipemaster.api.models.toRecipeItem
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipesViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private var _recipes: MutableLiveData<List<RecipeItem>> = MutableLiveData()
    val recipes: LiveData<List<RecipeItem>> = _recipes
    var isCategory: Boolean = true

    fun init(categoryItem: CategoriesItem) {
        when(categoryItem.tag) {
            "diets" -> { getDiets() }
            "cuisines" -> { getCuisines() }
            "mealTypes" -> { getMealTypes() }
            else -> {
                downloadRecipesFromApi(categoryItem.tag)
                isCategory = false
            }
        }
    }

    private fun getDiets() {
        _recipes.value = mutableListOf<RecipeItem>().apply {
            for(diet in Diet.values()) {
                add(RecipeItem(diet.imageUrl, diet.title))
            }
        }
    }

    private fun getCuisines() {
        _recipes.value = mutableListOf<RecipeItem>().apply {
            for(cuisine in Cuisine.values()) {
                add(RecipeItem(cuisine.imageUrl, cuisine.title))
            }
        }
    }

    private fun getMealTypes() {
        _recipes.value = mutableListOf<RecipeItem>().apply {
            for(mealType in MealType.values()) {
                add(RecipeItem(mealType.imageUrl, mealType.title))
            }
        }
    }

    private fun downloadRecipesFromApi(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = recipeRepository.getRandomRecipes(100, tag)
            if(result is Ok) {
                val recipesList = result.value
                withContext(Dispatchers.Main) {
                    _recipes.value = recipesList.map(Recipe::toRecipeItem)
                }
            }
        }
    }

}