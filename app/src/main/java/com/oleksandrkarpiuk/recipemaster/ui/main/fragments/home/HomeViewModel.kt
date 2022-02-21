package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.Ok
import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.api.models.Recipe
import com.oleksandrkarpiuk.recipemaster.mapping.toRecipeItem
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.models.*
import com.oleksandrkarpiuk.recipemaster.models.categories.Diet
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider
import kotlinx.coroutines.*

class HomeViewModel(
    private val recipeRepository: RecipeRepository,
    private val stringProvider: StringProvider
) : ViewModel() {

    private val categoryRecipesNumber = 10

    private var _categories: MutableLiveData<List<HomeCategoryItem>> = MutableLiveData(listOf())
    private var _dairyFreeDietRecipes: MutableLiveData<HomeCategoryItem> = MutableLiveData()
    private var _primalDietRecipes: MutableLiveData<HomeCategoryItem> = MutableLiveData()
    private var _lunchRecipes: MutableLiveData<HomeCategoryItem> = MutableLiveData()
    private var _mainDishesRecipes: MutableLiveData<HomeCategoryItem> = MutableLiveData()
    private var _sideDishesRecipes: MutableLiveData<HomeCategoryItem> = MutableLiveData()
    private var _soupsRecipes: MutableLiveData<HomeCategoryItem> = MutableLiveData()
    private var _dessertsRecipes: MutableLiveData<HomeCategoryItem> = MutableLiveData()
    private var _chineseRecipes: MutableLiveData<HomeCategoryItem> = MutableLiveData()
    private var _frenchRecipes: MutableLiveData<HomeCategoryItem> = MutableLiveData()
    private var _italianRecipes: MutableLiveData<HomeCategoryItem> = MutableLiveData()
    private var _mediterraneanRecipes: MutableLiveData<HomeCategoryItem> = MutableLiveData()
    private var _southernRecipes: MutableLiveData<HomeCategoryItem> = MutableLiveData()
    private var _error: MutableLiveData<String> = MutableLiveData("")

    val categories: LiveData<List<HomeCategoryItem>> = _categories
    val dairyFreeDietRecipes: LiveData<HomeCategoryItem> = _dairyFreeDietRecipes
    val primalDietRecipes: LiveData<HomeCategoryItem> = _primalDietRecipes
    val lunchRecipes: LiveData<HomeCategoryItem> = _lunchRecipes
    val mainDishesRecipes: LiveData<HomeCategoryItem> = _mainDishesRecipes
    val sideDishesRecipes: LiveData<HomeCategoryItem> = _sideDishesRecipes
    val soupsRecipes: LiveData<HomeCategoryItem> = _soupsRecipes
    val dessertsRecipes: LiveData<HomeCategoryItem> = _dessertsRecipes
    val chineseRecipes: LiveData<HomeCategoryItem> = _chineseRecipes
    val frenchRecipes: LiveData<HomeCategoryItem> = _frenchRecipes
    val italianRecipes: LiveData<HomeCategoryItem> = _italianRecipes
    val mediterraneanRecipes: LiveData<HomeCategoryItem> = _mediterraneanRecipes
    val southernRecipes: LiveData<HomeCategoryItem> = _southernRecipes
    val error: LiveData<String> = _error

    init {
        _categories.value = recipeRepository.getHomeCategories()
        viewModelScope.launch {
            _dairyFreeDietRecipes.value = HomeCategoryItem(stringProvider.getString(Diet.DAIRY_FREE.titleId), loadRecipes(Diet.DAIRY_FREE.tag), Diet.DAIRY_FREE.tag)
            _primalDietRecipes.value = HomeCategoryItem(stringProvider.getString(Diet.PRIMAL.titleId), loadRecipes(Diet.PRIMAL.tag), Diet.PRIMAL.tag)
//            _lunchRecipes.value = HomeCategoryItem(stringProvider.getString(MealType.LUNCH.titleId), loadRecipes(MealType.LUNCH.tag), MealType.LUNCH.tag)
//            _mainDishesRecipes.value = HomeCategoryItem(stringProvider.getString(MealType.MAIN_DISH.titleId), loadRecipes(MealType.MAIN_DISH.tag), MealType.MAIN_DISH.tag)
//            _sideDishesRecipes.value = HomeCategoryItem(stringProvider.getString(MealType.SIDE_DISH.titleId), loadRecipes(MealType.SIDE_DISH.tag), MealType.SIDE_DISH.tag)
//            _soupsRecipes.value = HomeCategoryItem(stringProvider.getString(MealType.SOUP.titleId), loadRecipes(MealType.SOUP.tag), MealType.SOUP.tag)
//            _dessertsRecipes.value = HomeCategoryItem(stringProvider.getString(MealType.DESSERT.titleId), loadRecipes(MealType.DESSERT.tag), MealType.DESSERT.tag)
//            _chineseRecipes.value = HomeCategoryItem(stringProvider.getString(Cuisine.CHINESE.titleId), loadRecipes(Cuisine.CHINESE.tag), Cuisine.CHINESE.tag)
//            _frenchRecipes.value = HomeCategoryItem(stringProvider.getString(Cuisine.FRENCH.titleId), loadRecipes(Cuisine.FRENCH.tag), Cuisine.FRENCH.tag)
//            _italianRecipes.value = HomeCategoryItem(stringProvider.getString(Cuisine.ITALIAN.titleId), loadRecipes(Cuisine.ITALIAN.tag), Cuisine.ITALIAN.tag)
//            _mediterraneanRecipes.value = HomeCategoryItem(stringProvider.getString(Cuisine.MEDITERRANEAN.titleId), loadRecipes(Cuisine.MEDITERRANEAN.tag), Cuisine.MEDITERRANEAN.tag)
//            _southernRecipes.value = HomeCategoryItem(stringProvider.getString(Cuisine.SOUTHERN.titleId), loadRecipes(Cuisine.SOUTHERN.tag), Cuisine.SOUTHERN.tag)
        }
    }

    private suspend fun loadRecipes(tag: String): List<RecipeItem> {
        val recipes = CoroutineScope(Dispatchers.IO).async {
            val result = recipeRepository.getRandomRecipes(categoryRecipesNumber, tag)
            return@async if(result is Ok) {
                val recipes = result.value
                recipes.map(Recipe::toRecipeItem)
            } else {
                withContext(Dispatchers.Main) { _error.value = stringProvider.getString(R.string.unknown_error) }
                listOf()
            }
        }
        return recipes.await()
    }

}