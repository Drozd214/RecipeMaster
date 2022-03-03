package com.oleksandrkarpiuk.recipemaster.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.Ok
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeItemMapper
import com.oleksandrkarpiuk.recipemaster.models.categories.BaseCategory
import com.oleksandrkarpiuk.recipemaster.models.categories.Cuisine
import com.oleksandrkarpiuk.recipemaster.models.categories.Diet
import com.oleksandrkarpiuk.recipemaster.models.categories.MealType
import com.oleksandrkarpiuk.recipemaster.models.recipes.BaseRecipeItem
import com.oleksandrkarpiuk.recipemaster.models.recipes.CategoryItem
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeItemModel
import com.oleksandrkarpiuk.recipemaster.utils.SpoonacularTags
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider
import kotlinx.coroutines.launch

class RecipesContainerViewModel(
    private val recipeRepository: RecipeRepository,
    private val stringProvider: StringProvider,
    private val recipeItemMapper: RecipeItemMapper
) : ViewModel() {

    private var _recipes: MutableLiveData<List<BaseRecipeItem>> = MutableLiveData()
    private var _title: MutableLiveData<String> = MutableLiveData("")
    private var _inLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    val recipes: LiveData<List<BaseRecipeItem>> = _recipes
    val title: LiveData<String> = _title
    val inLoading: LiveData<Boolean> = _inLoading

    fun refreshRecipes(title: String, tag: String) {
        if(inLoading.value!!) return
        _inLoading.value = true
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
            _inLoading.value = false
        }
    }

    private fun <T: BaseCategory> getCategories(categories: List<T>): List<CategoryItem> {
        return mutableListOf<CategoryItem>().apply {
            for (category in categories) {
                add(CategoryItem(category.imageUrl, stringProvider.getString(category.titleId), category.tag))
            }
        }
    }

    private suspend fun downloadRecipesFromApi(tag: String): List<RecipeItemModel> {
        val result = recipeRepository.getRandomRecipes(100, tag)
        return if(result is Ok) {
            result.value.map(recipeItemMapper::mapFromDomainToItem)
        } else {
            listOf()
        }
    }

    private fun changeTitle(title: String) {
        _title.value = title
    }

}