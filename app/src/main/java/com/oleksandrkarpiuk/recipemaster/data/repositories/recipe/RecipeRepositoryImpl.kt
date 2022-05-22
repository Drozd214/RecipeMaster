package com.oleksandrkarpiuk.recipemaster.data.repositories.recipe

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.api.models.RecipeDomainModel
import com.oleksandrkarpiuk.recipemaster.data.stores.recipe.RecipeDatabaseStore
import com.oleksandrkarpiuk.recipemaster.data.stores.recipe.RecipeRemoteStore
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeDatabaseMapper
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeSingleMapper
import com.oleksandrkarpiuk.recipemaster.models.*
import com.oleksandrkarpiuk.recipemaster.models.categories.Cuisine
import com.oleksandrkarpiuk.recipemaster.models.categories.Diet
import com.oleksandrkarpiuk.recipemaster.models.categories.MealType
import com.oleksandrkarpiuk.recipemaster.models.recipes.CategoryItem
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeSingleModel
import com.oleksandrkarpiuk.recipemaster.utils.SpoonacularTags
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider

class RecipeRepositoryImpl(
    private val recipeRemoteStore: RecipeRemoteStore,
    private val recipeDatabaseStore: RecipeDatabaseStore,
    private val stringProvider: StringProvider,
    private val recipeDatabaseMapper: RecipeDatabaseMapper,
    private val recipeSingleMapper: RecipeSingleMapper
) : RecipeRepository {

    override suspend fun getRandomRecipes(number: Int, tags: String): Result<List<RecipeDomainModel>, Throwable> {
        val resultApi = recipeRemoteStore.getRandomRecipes(number, tags)
        if(resultApi is Ok) {
            for(recipe in resultApi.value)
                recipeDatabaseStore.saveRecipeFromDomaine(recipeDatabaseMapper.mapFromDomainToDatabase(recipe))
        }
        return resultApi
    }

    override suspend fun saveRecipe(recipe: RecipeDomainModel) {
        recipeDatabaseStore.saveRecipeFromDomaine(recipeDatabaseMapper.mapFromDomainToDatabase(recipe))
    }

    override suspend fun updateRecipeFromApp(recipe: RecipeSingleModel) {
        recipeDatabaseStore.updateRecipeFromApp(recipeDatabaseMapper.mapFromSingleToDatabase(recipe))
    }

    override suspend fun getRecipeById(id: Int): Result<RecipeSingleModel, Throwable> {
        val databaseResult = recipeDatabaseStore.getRecipeById(id)
        return if(databaseResult is Ok) Ok(recipeSingleMapper.mapFromDatabaseToSingle(databaseResult.value))
        else Err(Throwable("Something went wrong"))
    }

    override fun getFavouriteRecipes(): Result<List<RecipeSingleModel>, Throwable> {
        val databaseResult = recipeDatabaseStore.getFavouriteRecipes()
        return if(databaseResult is Ok) Ok(databaseResult.value.map (recipeSingleMapper::mapFromDatabaseToSingle))
        else Err(Throwable("Something went wrong"))
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