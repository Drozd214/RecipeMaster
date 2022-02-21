package com.oleksandrkarpiuk.recipemaster.data.repositories.recipe

import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.api.models.Recipe
import com.oleksandrkarpiuk.recipemaster.models.HomeCategoryItem

interface RecipeRepository {

    suspend fun getRandomRecipes(number: Int, tags: String): Result<List<Recipe>, Throwable>
    suspend fun saveRecipe(recipe: Recipe)
    suspend fun getRecipeById(id: Int): Result<Recipe, Throwable>

    fun getHomeCategories(): List<HomeCategoryItem>

}