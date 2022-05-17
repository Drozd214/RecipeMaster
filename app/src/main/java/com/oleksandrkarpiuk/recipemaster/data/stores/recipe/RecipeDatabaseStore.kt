package com.oleksandrkarpiuk.recipemaster.data.stores.recipe

import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabaseModel

interface RecipeDatabaseStore {

    suspend fun saveRecipeFromDomaine(recipe: RecipeDatabaseModel)
    suspend fun updateRecipeFromApp(recipe: RecipeDatabaseModel)
    suspend fun getRecipeById(id: Int): Result<RecipeDatabaseModel, Throwable>

}