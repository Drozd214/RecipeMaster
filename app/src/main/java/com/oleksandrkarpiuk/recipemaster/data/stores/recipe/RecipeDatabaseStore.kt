package com.oleksandrkarpiuk.recipemaster.data.stores.recipe

import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabaseModel

interface RecipeDatabaseStore {

    suspend fun saveRecipe(recipe: RecipeDatabaseModel)
    suspend fun getRecipeById(id: Int): Result<RecipeDatabaseModel, Throwable>

}