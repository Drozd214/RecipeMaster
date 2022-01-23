package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleksandrkarpiuk.recipemaster.api.models.toRecipeItem
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.models.*
import com.oleksandrkarpiuk.recipemaster.utils.DietRecipesImage
import kotlinx.coroutines.*

class HomeViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val categoryRecipesNumber = 10

    private var _categories: MutableLiveData<List<CategoriesItem>> = MutableLiveData(listOf())
    val categories: LiveData<List<CategoriesItem>> = _categories

    private var _dairyFreeDietRecipes: MutableLiveData<CategoriesItem> = MutableLiveData()
    val dairyFreeDietRecipes: LiveData<CategoriesItem> = _dairyFreeDietRecipes

    private var _primalDietRecipes: MutableLiveData<CategoriesItem> = MutableLiveData()
    val primalDietRecipes: LiveData<CategoriesItem> = _primalDietRecipes

    private var _lunchRecipes: MutableLiveData<CategoriesItem> = MutableLiveData()
    val lunchRecipes: LiveData<CategoriesItem> = _lunchRecipes

    private var _mainDishesRecipes: MutableLiveData<CategoriesItem> = MutableLiveData()
    val mainDishesRecipes: LiveData<CategoriesItem> = _mainDishesRecipes

    private var _sideDishesRecipes: MutableLiveData<CategoriesItem> = MutableLiveData()
    val sideDishesRecipes: LiveData<CategoriesItem> = _sideDishesRecipes

    private var _soupsRecipes: MutableLiveData<CategoriesItem> = MutableLiveData()
    val soupsRecipes: LiveData<CategoriesItem> = _soupsRecipes

    private var _dessertsRecipes: MutableLiveData<CategoriesItem> = MutableLiveData()
    val dessertsRecipes: LiveData<CategoriesItem> = _dessertsRecipes

    private var _chineseRecipes: MutableLiveData<CategoriesItem> = MutableLiveData()
    val chineseRecipes: LiveData<CategoriesItem> = _chineseRecipes

    private var _frenchRecipes: MutableLiveData<CategoriesItem> = MutableLiveData()
    val frenchRecipes: LiveData<CategoriesItem> = _frenchRecipes

    private var _italianRecipes: MutableLiveData<CategoriesItem> = MutableLiveData()
    val italianRecipes: LiveData<CategoriesItem> = _italianRecipes

    private var _mediterraneanRecipes: MutableLiveData<CategoriesItem> = MutableLiveData()
    val mediterraneanRecipes: LiveData<CategoriesItem> = _mediterraneanRecipes

    private var _southernRecipes: MutableLiveData<CategoriesItem> = MutableLiveData()
    val southernRecipes: LiveData<CategoriesItem> = _southernRecipes

    private var _error: MutableLiveData<String> = MutableLiveData("")
    val error: LiveData<String> = _error

    private val diets = mutableListOf<RecipeItem>().apply {
        for(diet in Diet.values()) {
            if(diet.isVisibleInCategory) add(RecipeItem(diet.imageUrl, diet.title))
        }
    }

    private val cuisines = mutableListOf<RecipeItem>().apply {
        for(cuisine in Cuisine.values()) {
            add(RecipeItem(cuisine.imageUrl, cuisine.title))
        }
    }

    private val mealTypes = mutableListOf<RecipeItem>().apply {
        for(mealType in MealType.values()) {
            if(mealType.isVisibleInCategory) add(RecipeItem(mealType.imageUrl, mealType.title))
        }
    }

    init {
        _categories.value = mutableListOf<CategoriesItem>().apply {
            add(CategoriesItem("Diets", diets))
            add(CategoriesItem("Cuisines", cuisines))
            add(CategoriesItem("Meal Types", mealTypes))
            add(CategoriesItem(Diet.DAIRY_FREE.title, listOf()))
            add(CategoriesItem(Diet.PRIMAL.title, listOf()))
            add(CategoriesItem(MealType.LUNCH.title, listOf()))
            add(CategoriesItem(MealType.MAIN_DISH.title, listOf()))
            add(CategoriesItem(MealType.SIDE_DISH.title, listOf()))
            add(CategoriesItem(MealType.SOUP.title, listOf()))
            add(CategoriesItem(MealType.DESSERT.title, listOf()))
            add(CategoriesItem(Cuisine.CHINESE.title, listOf()))
            add(CategoriesItem(Cuisine.FRENCH.title, listOf()))
            add(CategoriesItem(Cuisine.ITALIAN.title, listOf()))
            add(CategoriesItem(Cuisine.MEDITERRANEAN.title, listOf()))
            add(CategoriesItem(Cuisine.SOUTHERN.title, listOf()))
        }
    }

    fun init() {
        viewModelScope.launch {
            _dairyFreeDietRecipes.value = CategoriesItem(Diet.DAIRY_FREE.title, loadRecipes(Diet.DAIRY_FREE.tag))
            _primalDietRecipes.value = CategoriesItem(Diet.PRIMAL.title, loadRecipes(Diet.PRIMAL.tag))
            _lunchRecipes.value = CategoriesItem(MealType.LUNCH.title, loadRecipes(MealType.LUNCH.tag))
            _mainDishesRecipes.value = CategoriesItem(MealType.MAIN_DISH.title, loadRecipes(MealType.MAIN_DISH.tag))
            _sideDishesRecipes.value = CategoriesItem(MealType.SIDE_DISH.title, loadRecipes(MealType.SIDE_DISH.tag))
            _soupsRecipes.value = CategoriesItem(MealType.SOUP.title, loadRecipes(MealType.SOUP.tag))
            _dessertsRecipes.value = CategoriesItem(MealType.DESSERT.title, loadRecipes(MealType.DESSERT.tag))
            _chineseRecipes.value = CategoriesItem(Cuisine.CHINESE.title, loadRecipes(Cuisine.CHINESE.tag))
            _frenchRecipes.value = CategoriesItem(Cuisine.FRENCH.title, loadRecipes(Cuisine.FRENCH.tag))
            _italianRecipes.value = CategoriesItem(Cuisine.ITALIAN.title, loadRecipes(Cuisine.ITALIAN.tag))
            _mediterraneanRecipes.value = CategoriesItem(Cuisine.MEDITERRANEAN.title, loadRecipes(Cuisine.MEDITERRANEAN.tag))
            _southernRecipes.value = CategoriesItem(Cuisine.SOUTHERN.title, loadRecipes(Cuisine.SOUTHERN.tag))
        }
    }

    private suspend fun loadRecipes(tag: String): List<RecipeItem> {
        val recipes = CoroutineScope(Dispatchers.IO).async {
            val result = recipeRepository.getRandomRecipes(categoryRecipesNumber, tag)
            return@async if(result.component1() != null) {
                val recipes = result.component1()
                val items = mutableListOf<RecipeItem>().apply {
                    for(recipe in recipes!!) {
                        add(recipe.toRecipeItem())
                    }
                }
                items
            } else {
                withContext(Dispatchers.Main) { _error.value = "Something went wrong" }
                listOf()
            }
        }
        return recipes.await()
    }
}