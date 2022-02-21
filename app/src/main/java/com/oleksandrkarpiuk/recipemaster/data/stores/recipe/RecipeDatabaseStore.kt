package com.oleksandrkarpiuk.recipemaster.data.stores.recipe

import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.api.models.Recipe
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabase

interface RecipeDatabaseStore {

    suspend fun saveRecipe(recipe: RecipeDatabase)

    suspend fun getRecipeById(id: Int): Result<RecipeDatabase, Throwable>

}