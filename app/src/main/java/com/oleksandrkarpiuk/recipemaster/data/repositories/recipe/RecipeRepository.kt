package com.oleksandrkarpiuk.recipemaster.data.repositories.recipe

import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.api.models.RecipeDomainModel
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabaseModel
import com.oleksandrkarpiuk.recipemaster.models.HomeCategoryItem
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeSingleModel

interface RecipeRepository {

    suspend fun getRandomRecipes(number: Int, tags: String): Result<List<RecipeDomainModel>, Throwable>
    suspend fun saveRecipe(recipe: RecipeDomainModel)
    suspend fun updateRecipeFromApp(recipe: RecipeSingleModel)
    suspend fun getRecipeById(id: Int): Result<RecipeSingleModel, Throwable>

    fun getHomeCategories(): List<HomeCategoryItem>

}