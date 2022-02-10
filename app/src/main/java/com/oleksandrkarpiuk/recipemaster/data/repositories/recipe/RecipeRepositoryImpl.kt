package com.oleksandrkarpiuk.recipemaster.data.repositories.recipe

import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.api.models.Recipe
import com.oleksandrkarpiuk.recipemaster.data.stores.recipe.RecipeRemoteStore
import com.oleksandrkarpiuk.recipemaster.models.*
import com.oleksandrkarpiuk.recipemaster.models.categories.Cuisine
import com.oleksandrkarpiuk.recipemaster.models.categories.Diet
import com.oleksandrkarpiuk.recipemaster.models.categories.MealType
import com.oleksandrkarpiuk.recipemaster.utils.SpoonacularTags
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider

class RecipeRepositoryImpl(
    private val recipeRemoteStore: RecipeRemoteStore,
    private val stringProvider: StringProvider
) : RecipeRepository {

    override suspend fun getRandomRecipes(number: Int, tags: String): Result<List<Recipe>, Throwable> {
        return recipeRemoteStore.getRandomRecipes(number, tags)
    }

    override fun getHomeCategories(): List<HomeCategoryItem> {
        val diets = mutableListOf<CategoryItem>().apply {
            for(diet in Diet.values()) {
                if(diet.isVisibleInCategory) add(CategoryItem(diet.imageUrl, stringProvider.getString(diet.titleId), diet.tag))
            }
        }

        val cuisines = mutableListOf<CategoryItem>().apply {
            for(cuisine in Cuisine.values()) {
                if(cuisine.isVisibleInCategory) add(CategoryItem(cuisine.imageUrl, stringProvider.getString(cuisine.titleId), cuisine.tag))
            }
        }

        val mealTypes = mutableListOf<CategoryItem>().apply {
            for(mealType in MealType.values()) {
                if(mealType.isVisibleInCategory) add(CategoryItem(mealType.imageUrl, stringProvider.getString(mealType.titleId), mealType.tag))
            }
        }

        return mutableListOf<HomeCategoryItem>().apply {
            add(HomeCategoryItem(stringProvider.getString(R.string.diets), diets, SpoonacularTags.DIETS))
            add(HomeCategoryItem(stringProvider.getString(R.string.cuisines), cuisines, SpoonacularTags.CUISINES))
            add(HomeCategoryItem(stringProvider.getString(R.string.meal_types), mealTypes, SpoonacularTags.MEAL_TYPES))
            add(HomeCategoryItem(stringProvider.getString(Diet.DAIRY_FREE.titleId), listOf(), Diet.DAIRY_FREE.tag))
            add(HomeCategoryItem(stringProvider.getString(Diet.PRIMAL.titleId), listOf(), Diet.PRIMAL.tag))
            add(HomeCategoryItem(stringProvider.getString(MealType.LUNCH.titleId), listOf(), MealType.LUNCH.tag))
            add(HomeCategoryItem(stringProvider.getString(MealType.MAIN_DISH.titleId), listOf(), MealType.MAIN_DISH.tag))
            add(HomeCategoryItem(stringProvider.getString(MealType.SIDE_DISH.titleId), listOf(), MealType.SIDE_DISH.tag))
            add(HomeCategoryItem(stringProvider.getString(MealType.SOUP.titleId), listOf(), MealType.SOUP.tag))
            add(HomeCategoryItem(stringProvider.getString(MealType.DESSERT.titleId), listOf(), MealType.DESSERT.tag))
            add(HomeCategoryItem(stringProvider.getString(Cuisine.CHINESE.titleId), listOf(), Cuisine.CHINESE.tag))
            add(HomeCategoryItem(stringProvider.getString(Cuisine.FRENCH.titleId), listOf(), Cuisine.FRENCH.tag))
            add(HomeCategoryItem(stringProvider.getString(Cuisine.ITALIAN.titleId), listOf(), Cuisine.ITALIAN.tag))
            add(HomeCategoryItem(stringProvider.getString(Cuisine.MEDITERRANEAN.titleId), listOf(), Cuisine.MEDITERRANEAN.tag))
            add(HomeCategoryItem(stringProvider.getString(Cuisine.SOUTHERN.titleId), listOf(), Cuisine.SOUTHERN.tag))
        }
    }

}