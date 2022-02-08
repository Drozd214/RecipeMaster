package com.oleksandrkarpiuk.recipemaster.ui.recipes

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.Ok
import com.oleksandrkarpiuk.recipemaster.api.models.Recipe
import com.oleksandrkarpiuk.recipemaster.api.models.toRecipeItem
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.models.*
import com.oleksandrkarpiuk.recipemaster.models.categories.BaseCategory
import com.oleksandrkarpiuk.recipemaster.models.categories.Cuisine
import com.oleksandrkarpiuk.recipemaster.models.categories.Diet
import com.oleksandrkarpiuk.recipemaster.models.categories.MealType
import com.oleksandrkarpiuk.recipemaster.utils.SpoonacularTags
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipesViewModel(
    private val recipeRepository: RecipeRepository,
    private val stringProvider: StringProvider
) : ViewModel() {

    private var _recipes: MutableLiveData<List<RecipeItem>> = MutableLiveData()
    val recipes: LiveData<List<RecipeItem>> = _recipes
    var isCategory: Boolean = true

    fun init(categoryItem: HomeCategoryItem) {
        viewModelScope.launch {
            _recipes.value = when (categoryItem.tag) {
                SpoonacularTags.DIETS -> { getCategories(Diet.values().toList()) }
                SpoonacularTags.CUISINES -> { getCategories(Cuisine.values().toList())}
                SpoonacularTags.MEAL_TYPES -> { getCategories(MealType.values().toList()) }
                else -> {
                    isCategory = false
                    downloadRecipesFromApi(categoryItem.tag)
                }
            }
        }
    }

    private fun <T: BaseCategory> getCategories(categories: List<T>): List<RecipeItem> {
        return mutableListOf<RecipeItem>().apply {
            for (mealType in categories) {
                add(RecipeItem(mealType.imageUrl, stringProvider.getString(mealType.titleId)))
            }
        }
    }

    private suspend fun downloadRecipesFromApi(tag: String): List<RecipeItem> {
        val result = recipeRepository.getRandomRecipes(100, tag)
        return if(result is Ok) {
            result.value.map(Recipe::toRecipeItem)
        } else {
            listOf()
        }
    }

}