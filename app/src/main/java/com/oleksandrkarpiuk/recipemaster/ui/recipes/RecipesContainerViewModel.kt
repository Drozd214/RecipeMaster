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

class RecipesContainerViewModel(
    private val recipeRepository: RecipeRepository,
    private val stringProvider: StringProvider
) : ViewModel() {

    private var _recipes: MutableLiveData<List<BaseRecipeItem>> = MutableLiveData()
    private var _title: MutableLiveData<String> = MutableLiveData("")

    val recipes: LiveData<List<BaseRecipeItem>> = _recipes
    val title: LiveData<String> = _title

    fun refreshRecipes(title: String, tag: String) {
        viewModelScope.launch {
            _recipes.value = when (tag) {
                SpoonacularTags.DIETS -> { getCategories(Diet.values().toList()) }
                SpoonacularTags.CUISINES -> { getCategories(Cuisine.values().toList())}
                SpoonacularTags.MEAL_TYPES -> { getCategories(MealType.values().toList()) }
                else -> {
                    downloadRecipesFromApi(tag)
                }
            }
            changeTitle(title)
        }
    }

    private fun <T: BaseCategory> getCategories(categories: List<T>): List<CategoryItem> {
        return mutableListOf<CategoryItem>().apply {
            for (category in categories) {
                add(CategoryItem(category.imageUrl, stringProvider.getString(category.titleId), category.tag))
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

    fun changeTitle(title: String) {
        _title.value = title
    }

}